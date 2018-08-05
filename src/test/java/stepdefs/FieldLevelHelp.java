package stepdefs;

import controllers.NavigationController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import helpers.Timer;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import views.KiwiSaverRetirementCalculatorView;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static helpers.StepManagement.getCurrentStepRef;

public class FieldLevelHelp implements En {

    private static Category log = Logger.getLogger(Timer.class);

    private NavigationController navigationController = new NavigationController();
    private KiwiSaverRetirementCalculatorView kiwiSaverRetirementCalculatorView = new KiwiSaverRetirementCalculatorView();

    @Given("^I am on the \"([^\"]*)\" page$")
    public void iAmOnThePage(String targetLocation) throws IOException {
        new Timer(getCurrentStepRef() + ": Navigate to the KiwSaver Retirement Calculator page"){{
            navigationController.navigateTo(targetLocation);
        }}.timeElapsed(true, false);
    }

    @When("^I click in the information icon for the \"([^\"]*)\" control$")
    public void iClickInTheInformationIconForTheControl(String controlType){

        // The calculator widget is an iframe, so we have to switch to it before accessing it's elements
        switchTo().frame(kiwiSaverRetirementCalculatorView.getCalculatorWidget());
        log.info("Switching to calculator widget frame");
        switch(controlType.toLowerCase().replaceAll(" ", "").replaceAll(",", "")){
            case "currentage":
                kiwiSaverRetirementCalculatorView.getCurrentAgeInfoButton().click();
                break;
            case "currentkiwisaverbalance":
                kiwiSaverRetirementCalculatorView.getCurrentKiwiSaverBalanceInfoButton().click();
                break;
            default:
                throw new IllegalArgumentException(String.format("'%s' is not a valid field-level-help component in this context", controlType));
        }
        switchTo().defaultContent();
        log.info("Switching to default content");
    }

    @Then("^I see the information \"([^\"]*)\" displayed for the \"([^\"]*)\" control$")
    public void iSeeTheInformationDisplayedForTheControl(String expectedMessage, String controlType){
        // The calculator widget is an iframe, so we have to switch to it before accessing it's elements
        switchTo().frame(kiwiSaverRetirementCalculatorView.getCalculatorWidget());
        log.info("Switching to calculator widget frame");
        switch(controlType.toLowerCase().replaceAll(" ", "").replaceAll(",", "")){
            case "currentage":
                kiwiSaverRetirementCalculatorView.getCurrentAgeInfoDisplay().
                        shouldBe(visible.because("The field-level help display for 'Current Age' should be visible")).
                        shouldHave(text(expectedMessage).because(String.format("The field level help display should contain '%s' but contained '%s'",
                                expectedMessage, kiwiSaverRetirementCalculatorView.getCurrentAgeInfoDisplay().getText())));
                break;
            case "currentkiwisaverbalance":
                kiwiSaverRetirementCalculatorView.getCurrentKiwiSaverBalanceInfoDisplay().
                        shouldBe(visible.because("The field-level help display for 'Current KiwiSaver Balance' should be visible")).
                        shouldHave(text(expectedMessage).because(String.format("The field level help display should contain '%s' but contained '%s'",
                                expectedMessage, kiwiSaverRetirementCalculatorView.getCurrentKiwiSaverBalanceInfoDisplay().getText())));
                break;
            default:
                throw new IllegalArgumentException(String.format("'%s' is not a valid field-level-help component in this context", controlType));
        }
        switchTo().defaultContent();
        log.info("Switching to default content");
    }

}
