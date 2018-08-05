package views;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class KiwiSaverInfoView {

    public void shouldBeVisible(){
        $(By.className("sw-page-heading")).shouldHave(text("Westpac KiwiSaver Scheme Risk Profiler and Retirement Calculator"));
    }

    public SelenideElement getClickHereToGetStartedButton() {
        return $(By.linkText("Click here to get started.")).shouldBe(visible.because("The Click here to get started button should be visible"));
    }
}
