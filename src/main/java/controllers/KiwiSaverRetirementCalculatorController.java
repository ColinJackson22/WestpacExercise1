package controllers;

import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import views.KiwiSaverRetirementCalculatorView;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class KiwiSaverRetirementCalculatorController {

    private static Category log = Logger.getLogger(KiwiSaverRetirementCalculatorController.class);

    private KiwiSaverRetirementCalculatorView kiwiSaverRetirementCalculatorView = new KiwiSaverRetirementCalculatorView();

    public void calculateProjectBalance(String age, String employmentStatus, String currentSalary, String contribution,
                                        String volContrib, String volContribRate, String currentBalance, String pir, String savingGoalsRequirement, String profileType){

        if(!pir.endsWith("%")) pir += "%";

        switchTo().frame(kiwiSaverRetirementCalculatorView.getCalculatorWidget());

        // Current Age field
        if(!age.equals("")) {
            kiwiSaverRetirementCalculatorView.getCurrentAgeField().sendKeys(age);
            kiwiSaverRetirementCalculatorView.getCurrentAgeField().shouldHave(value(age));
        }

        // Employment Status selector
        setSelector(employmentStatus, kiwiSaverRetirementCalculatorView.getEmploymentStatusSelector());
        kiwiSaverRetirementCalculatorView.getEmploymentStatusSelector().shouldHave(text(employmentStatus));

        // Some fields only available to Employed
        if(employmentStatus.equals("Employed")) {

            // Current Salary field
            kiwiSaverRetirementCalculatorView.getSalaryOrWagesPerYearField().shouldBe(visible);
            kiwiSaverRetirementCalculatorView.getSalaryOrWagesPerYearField().sendKeys(currentSalary);
            kiwiSaverRetirementCalculatorView.getSalaryOrWagesPerYearField().shouldHave(value(currentSalary));

            // KiwiSaver Contributions radio-button
            kiwiSaverRetirementCalculatorView.getKiwiSaverContributionRadioButton(contribution).click();
        }

        // Set PIR rate
        setSelector(pir, kiwiSaverRetirementCalculatorView.getPrescribedInvestorRateSelector());
        kiwiSaverRetirementCalculatorView.getPrescribedInvestorRateSelector().shouldHave(text(pir));

        // Set Current KiwiSave Balance - this control won't accept a standard sendKeys because it expects a keyboard event
        if(!currentBalance.equals("")) {
            kiwiSaverRetirementCalculatorView.getCurrentKiwiSaverBalanceField().click();
            sendKeysAsAction(currentBalance);
        }

        // Set the Voluntary Contribution value
        if(!volContrib.equals("")) {
            setSelector(volContribRate, kiwiSaverRetirementCalculatorView.getVoluntaryContributionSelector());
            kiwiSaverRetirementCalculatorView.getVoluntaryContributionSelector().shouldHave(text(volContribRate));
            kiwiSaverRetirementCalculatorView.getVoluntaryContributionField().sendKeys(volContrib);
            kiwiSaverRetirementCalculatorView.getVoluntaryContributionField().shouldHave(value(volContrib));
        }

        // Set the risk profile
        kiwiSaverRetirementCalculatorView.getRiskProfileRadioButton(profileType).click();

        // Set the Savings Goal value
        if(!savingGoalsRequirement.equals("")) {
            kiwiSaverRetirementCalculatorView.getSavingsGoalField().sendKeys(savingGoalsRequirement);
            kiwiSaverRetirementCalculatorView.getSavingsGoalField().shouldHave(value(savingGoalsRequirement));
        }

        kiwiSaverRetirementCalculatorView.getViewProjectionButton().shouldBe(enabled.because("The View Projection button should now be enabled"));
        kiwiSaverRetirementCalculatorView.getViewProjectionButton().click();
    }

    private void setSelector(String value, SelenideElement selectorElement) {

        if (!value.equals("")) {
            selectorElement.click();
            selectorElement.$(By.className("option-list")).shouldBe(visible.because("The selector options should be visible"));
            for (SelenideElement option : selectorElement.$$(By.className("option-item"))) {
                if (option.getText().equals(value)) {
                    option.click();
                    break;
                }
            }
        }
    }

    private void sendKeysAsAction(String inputText){
        Actions make = new Actions(getWebDriver());
        Action kbEvents = make.sendKeys(inputText).build();
        kbEvents.perform();
    }

}
