package stepdefs;

import controllers.KiwiSaverRetirementCalculatorController;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import views.KiwiSaverRetirementCalculatorView;

import static com.codeborne.selenide.Condition.visible;

public class KiwiSaverCalculatorCalculations {

    private static Category log = Logger.getLogger(KiwiSaverCalculatorCalculations.class);

    private KiwiSaverRetirementCalculatorController kiwiSaverRetirementCalculatorController = new KiwiSaverRetirementCalculatorController();
    private KiwiSaverRetirementCalculatorView kiwiSaverRetirementCalculatorView = new KiwiSaverRetirementCalculatorView();

    @When("^I enter calculation parameters \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iEnterCalculationParameters(String age, String employmentStatus, String currentSalary, String contribution,
                                            String volContrib, String volContribRate, String currentBalance, String pir, String savingGoalsRequirement, String profileType) {
        // Would generally do some validation here, but will just push the params unchecked for the sake of the exercise
        kiwiSaverRetirementCalculatorController.calculateProjectBalance(age, employmentStatus, currentSalary, contribution,
                volContrib, volContribRate, currentBalance, pir, savingGoalsRequirement, profileType);
        kiwiSaverRetirementCalculatorView.getProjectedBalanceFrame().shouldBe(visible.because("The Results should be visible"));
    }

    @Then("^I see the project balance at retirement$")
    public void iSeeTheProjectBalanceAtRetirement(){
        kiwiSaverRetirementCalculatorView.getProjectedBalanceFrame().shouldBe(visible.because("The Results should be visible"));
        String projectedBalance = kiwiSaverRetirementCalculatorView.getProjectedBalanceResult().getText().replace("\n", "");
        log.info(String.format("The search criteria provided resulted in a Projected Balance at Retirement '%s'", projectedBalance));
    }
}

