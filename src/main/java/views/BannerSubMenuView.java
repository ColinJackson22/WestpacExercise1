package views;

import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BannerSubMenuView {

    private static Category log = Logger.getLogger(BannerSubMenuView.class);

    public void shouldBeVisible(){
        $(By.id("ubermenu-ps")).shouldBe(visible.because("The ubermenu should be visible"));
    }

    public SelenideElement getKiwiSaverLink(){
        return $(By.id("ubermenu-section-link-kiwisaver-ps")).shouldBe(visible.because("The KiwiSaver button should be visible"));
    }

    public SelenideElement getKiwiSaverCalculatorsButton() {
        return $(By.id("ubermenu-item-cta-kiwisaver-calculators-ps")).shouldBe(visible.because("The KiwSaver Calculators button should be visible"));
    }
}
