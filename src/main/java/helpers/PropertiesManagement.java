package helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManagement {

    private static InputStream inputStream;

    public static Properties getPropertyCollection(String filename) throws IOException {

        Class currentClass = new Object() { }.getClass().getEnclosingClass();

        try {
            Properties properties = new Properties();
            inputStream = currentClass.getClassLoader().getResourceAsStream(filename);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + filename + "' not found in the classpath");
            }
            return properties;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null) { inputStream.close(); }
        }
        return null;
    }
}
