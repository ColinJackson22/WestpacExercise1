package stepdefs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static helpers.PropertiesManagement.getPropertyCollection;

public class CucumberHooks {

    private static Category log = Logger.getLogger(CucumberHooks.class);
    private Instant startTime;

    @Before
    public void beforeScenario(Scenario scenario){

        startTime = Instant.now();
        try {
            System.setProperty("webdriver.chrome.driver", Objects.requireNonNull(getPropertyCollection("system.config.properties")).getProperty("chromedriver"));
            Configuration.baseUrl = Objects.requireNonNull(getPropertyCollection("application.config.properties")).getProperty("application.baseURL");
            Configuration.browser = Objects.requireNonNull(getPropertyCollection("application.config.properties")).getProperty("browser.type");
            Configuration.browserSize = String.format("%sx%s",
                    Objects.requireNonNull(getPropertyCollection("application.config.properties")).getProperty("browser.width"),
                    Objects.requireNonNull(getPropertyCollection("application.config.properties")).getProperty("browser.height"));
            Configuration.startMaximized = Boolean.parseBoolean(Objects.requireNonNull(getPropertyCollection("application.config.properties")).getProperty("browser.startMaximized"));
        } catch (NullPointerException | IOException e){
            e.printStackTrace();
        }
        open("/");
        log.info(String.format("Opening browser at base URL '%s' for scenario '%s'", Configuration.baseUrl, scenario.getName()));
    }

    @After
    public void afterScenario(Scenario scenario){
        log.info(String.format("Completed scenario '%s' in %s seconds", scenario.getName(), Duration.between(startTime, Instant.now()).getSeconds()));
        WebDriverRunner.getWebDriver().close();
    }

}
