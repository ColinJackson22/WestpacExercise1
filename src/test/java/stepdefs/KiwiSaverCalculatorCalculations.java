package stepdefs;

import controllers.KiwiSaverRetirementCalculatorController;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import views.KiwiSaverRetirementCalculatorView;

import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.MatcherAssert.assertThat;

public class KiwiSaverCalculatorCalculations {

    private KiwiSaverRetirementCalculatorController kiwiSaverRetirementCalculatorController = new KiwiSaverRetirementCalculatorController();
    private KiwiSaverRetirementCalculatorView kiwiSaverRetirementCalculatorView = new KiwiSaverRetirementCalculatorView();

    @When("^I enter calculation parameters \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iEnterCalculationParameters(String age, String employmentStatus, String currentSalary, String contribution,
                                            String volContrib, String volContribRate, String currentBalance, String pir, String savingGoalsRequirement, String profileType){
        // Would generally do some validation here, but will just push the params unchecked for the sake of the exercise
        kiwiSaverRetirementCalculatorController.calculateProjectBalance(age, employmentStatus, currentSalary, contribution,
                volContrib, volContribRate, currentBalance, pir, savingGoalsRequirement, profileType);
        kiwiSaverRetirementCalculatorView.getProjectedBalanceFrame().shouldBe(visible.because("The Results should be visible"));
    }

    @Then("^I see the project balance at retirement matches the expected value \"([^\"]*)\"$")
    public void iSeeTheProjectBalanceAtRetirementMatchesTheExpectedValue(String expectedBalance){
        kiwiSaverRetirementCalculatorView.getProjectedBalanceFrame().shouldBe(visible.because("The Results should be visible"));
        String projectedBalance = kiwiSaverRetirementCalculatorView.getProjectedBalanceResult().
                getText().replaceAll(",", "").replace("$\n", "");
        assertThat(String.format("The balance was $'%s', expected $'%s'", projectedBalance, expectedBalance), projectedBalance.contains(expectedBalance));
    }
}

