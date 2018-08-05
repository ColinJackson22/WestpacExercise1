package views;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class KiwiSaverRetirementCalculatorView {

    private SelenideElement savingsGoalField;

    public void shouldBeVisible(){
        $(By.className("content-inner-header")).shouldHave(text("KiwiSaver Retirement Calculator"));
        getCalculatorWidget();
    }

    public SelenideElement getCalculatorWidget(){
        return $(By.tagName("iframe")).shouldBe(visible.because("The calculator widget should be visible"));
    }

    public SelenideElement getCurrentAgeInfoButton() {
        return $(By.cssSelector("div[help-id='CurrentAge']")).$(By.tagName("button")).shouldBe(visible.because("The Current Age Help button should be visible"));
    }

    public SelenideElement getCurrentAgeInfoDisplay() {
        return $(By.cssSelector("div[label='Current age']")).$(By.className("field-message"));
    }

    public SelenideElement getCurrentKiwiSaverBalanceInfoButton() {
        return $(By.cssSelector("div[help-id='KiwiSaverBalance']")).$(By.tagName("button")).shouldBe(visible.because("The Current KiwiSaver Balance Help button should be visible"));
    }

    public SelenideElement getCurrentKiwiSaverBalanceInfoDisplay() {
        return $(By.cssSelector("div[label='Current KiwiSaver balance']")).$(By.className("field-message"));
    }

    public SelenideElement getCurrentAgeField() {
        return $(By.cssSelector("div[label='Current age']")).$(By.tagName("input")).shouldBe(visible.because("The Current Age input field should be visible"));
    }

    public SelenideElement getEmploymentStatusSelector() {
        return $(By.cssSelector("div[label='Employment status']")).$(By.className("select-control")).shouldBe(visible.because("The Employment Status selector should be visible"));
    }


    public SelenideElement getSalaryOrWagesPerYearField() {
        return $(By.cssSelector("div[label='Salary or wages per year (before tax)']")).$(By.tagName("input")).
                shouldBe(visible.because("The Salary or wages per year (before tax) input field should be visible"));
    }

    public SelenideElement getKiwiSaverContributionRadioButton(String rateType) {
        return $(By.cssSelector("div[label='KiwiSaver member contribution']")).$(By.cssSelector("div[class*='radio-control'][value='" + rateType + "']"));
    }

    public SelenideElement getPrescribedInvestorRateSelector() {
        return $(By.cssSelector("div[label='Prescribed investor rate (PIR)']")).$(By.className("select-control")).shouldBe(visible.because("The PIR selector should be visible"));
    }

    public SelenideElement getCurrentKiwiSaverBalanceField() {
        return $(By.className("wpnib-field-kiwi-saver-balance")).$(By.tagName("input")).
                shouldBe(visible.because("The Current KiwiSaver Balance input field should be visible"));
    }

    public SelenideElement getVoluntaryContributionSelector() {
        return $(By.cssSelector("div[label='Voluntary contributions']")).$(By.className("select-control")).
                shouldBe(visible.because("The Voluntary Contributions selector should be visible"));
    }

    public SelenideElement getVoluntaryContributionField() {
        return $(By.cssSelector("div[label='Voluntary contributions']")).$(By.tagName("input")).shouldBe(visible.because("The Voluntary Contributions input field should be visible"));
    }

    public SelenideElement getRiskProfileRadioButton(String profileType) {
        return $(By.cssSelector("div[label='Risk profile']")).$(By.cssSelector("div[class*='radio-control'][value='" + profileType + "']"));
    }

    public SelenideElement getSavingsGoalField() {
        return $(By.cssSelector("div[label='Savings goal at retirement']")).$(By.tagName("input")).shouldBe(visible.because("The Savings goal at retirement input field should be visible"));
    }

    public SelenideElement getViewProjectionButton() {
        return $(By.className("btn-results-reveal"));
    }

    public SelenideElement getProjectedBalanceFrame() {
        return $(By.className("results-field-group-set"));
    }

    public SelenideElement getProjectedBalanceResult() {
        return getProjectedBalanceFrame().$(By.className("result-value"));
    }
}
