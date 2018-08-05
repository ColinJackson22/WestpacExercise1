package views;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class KiwiSaverRetirementCalculatorView {

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
}
