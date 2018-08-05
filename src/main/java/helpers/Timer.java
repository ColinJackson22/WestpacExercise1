package helpers;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.poi.POIXMLProperties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.InvalidArgumentException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import static com.codeborne.selenide.WebDriverRunner.url;
import static helpers.PropertiesManagement.getPropertyCollection;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

public class Timer{

    private String timerName;
    private long started;
    private final String FILEPATH = Objects.requireNonNull(getPropertyCollection("system.config.properties")).getProperty("timerRecordingLocation");
    private final String FILENAME = Objects.requireNonNull(getPropertyCollection("system.config.properties")).getProperty("timerRecordingFileName");
    private final Boolean RECORDING_ENABLED = Boolean.valueOf(Objects.requireNonNull(getPropertyCollection("system.config.properties")).getProperty("timerRecordingEnable"));
    private static Category log = Logger.getLogger(Timer.class);

    public Timer(String timerName) throws IOException {
        if(timerName.equals("")){ throw new InvalidArgumentException("The timer descriptive name cannot be null"); }
        if(null==FILEPATH){ throw new InvalidArgumentException("The log file defined location in cannot be null"); }
        if(null==FILENAME){ throw new InvalidArgumentException("The log file defined filename in cannot be null"); }
        this.timerName = timerName;
        this.started = System.currentTimeMillis();
    }

    public void timeElapsed(){
        this.timeElapsed(true, false);
    }

    public void timeElapsed(Boolean recordIt){
        this.timeElapsed(true, recordIt);
    }

    public void timeElapsed(Boolean displayIt, Boolean recordIt){
        long elapsed = (System.currentTimeMillis() - started) / 1000;
        if(displayIt) {
            log.info(String.format("Execution of '%s' took %d seconds to perform (%s) @ '%s'",
                    timerName, elapsed, LocalDate.now().toString() + ":" + LocalTime.now(), url()));
        }
        if(RECORDING_ENABLED && recordIt) {
            Workbook wb = getWorkbook();
            putTimingEntry(wb, elapsed);
            closeWorkbook(wb);
        }
    }

    private Workbook getWorkbook() {
        String fileLocator = this.FILEPATH + "\\" + this.FILENAME + ".xlsx";
        if (folderExists(this.FILEPATH)){
            if (!fileExists(fileLocator)) {
                return createStructuredExcelFile(fileLocator);
            }else{
                try {
                    XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(fileLocator));
                    FileOutputStream fileOut = new FileOutputStream(fileLocator);
                    wb.write(fileOut);
                    fileOut.close();
                    return wb;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new IllegalArgumentException("The timer logging directory for Timer() is not a valid directory on this local system.\n\r" +
                "Please either create this directory, or adjust the target directory value 'timerRecordingLocation' in 'system.config.properties'.\n\r" +
                "Current value: '" + this.FILEPATH + "'");
    }

    private Boolean fileExists(String fileLocator){
        File f = new File(fileLocator);
        return (f.isFile() && f.canRead() && f.canWrite());
    }

    private Boolean folderExists(String filePath){
        return new File(filePath).isDirectory();
    }

    private void closeWorkbook(Workbook wb){
        try {
            FileOutputStream fileOut = new FileOutputStream(this.FILEPATH + "\\" + this.FILENAME + ".xlsx");
            wb.write(fileOut);
            fileOut.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Create  a new workbook if the config points to one that doesn't exist
     */
    private Workbook createStructuredExcelFile(String fileLocator){

        // Create a new workbook
        XSSFWorkbook wb = new XSSFWorkbook();
        wb.createSheet("Process Timings");
        POIXMLProperties props = wb.getProperties();
        POIXMLProperties.CoreProperties coreProp = props.getCoreProperties();

        // Set some core properties
        coreProp.setCreator("Automation");
        coreProp.setDescription("Stores process timings for test automation "); //set Description
        coreProp.setTitle("Automation Process Timings"); //Title of the document
        coreProp.setSubjectProperty("Debug"); //Subject
        coreProp.setCategory("Timings"); //category

        // Set some extended properties
        POIXMLProperties.ExtendedProperties extProp=props.getExtendedProperties();
        extProp.getUnderlyingProperties().setCompany("Westpac");
        extProp.getUnderlyingProperties().setTemplate("XSSF");
        extProp.getUnderlyingProperties().setManager("Automation");

        // Write the file
        try {
            FileOutputStream fileOut = new FileOutputStream(fileLocator);
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /*
    Enter the current timer details into the workbook in the appropriate place
     */
    private void putTimingEntry(Workbook wb, Long elapsed){
        Sheet sheet = wb.getSheet("Process Timings");
        Row newRow;
        Cell titleCell;
        Cell valueCell;
        if(!(sheet.getRow(0) == null)){
            for (Row row : sheet) {
                if (row.getCell(0).getStringCellValue().equals(this.timerName)) {
                    valueCell = row.createCell(row.getLastCellNum());
                    valueCell.setCellType(NUMERIC);
                    valueCell.setCellValue(elapsed);
                    return;
                }
            }
            newRow = sheet.createRow(sheet.getLastRowNum() + 1);
        }else{
            newRow = sheet.createRow(0);
        }
        titleCell = newRow.createCell(0);
        valueCell = newRow.createCell(1);
        titleCell.setCellType(STRING);
        titleCell.setCellValue(this.timerName);
        valueCell.setCellType(NUMERIC);
        valueCell.setCellValue(elapsed);
    }


}
