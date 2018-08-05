import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Colin Jackson on 05/08/2018
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features/FieldLevelHelp.feature",
        tags={"-@wip"},
        format = {  "pretty",
                    "html:target/cucumber-report/",
                    "json:target/cucumber-report/cucumber.json",
                    "rerun:target/cucumber-report/rerun.txt" },
        glue = {"stepdefs"})

public class Run_FieldLevelHelp {
}

