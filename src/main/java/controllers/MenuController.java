package controllers;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import views.BannerSubMenuView;
import views.KiwiSaverInfoView;

import static com.codeborne.selenide.Condition.visible;

public class MenuController {

    private static Category log = Logger.getLogger(NavigationController.class);

    private BannerSubMenuView bannerSubMenuView = new BannerSubMenuView();
    private KiwiSaverInfoView kiwiSaverInfoView = new KiwiSaverInfoView();

    public void bannerSubMenuSelector(String targetMenuItem){

        // TODO: Add new target locations as required
        switch(targetMenuItem.toLowerCase().replaceAll(" ", "").replaceAll(",", "")){
            case "kiwisaver":
                log.info("Navigating to the KiwiSaver page");
                bannerSubMenuView.shouldBeVisible();
                bannerSubMenuView.getKiwiSaverLink().shouldBe(visible.because(String.format("The target link '%s' should be visible", targetMenuItem))).hover();
                bannerSubMenuView.getKiwiSaverCalculatorsButton().click();
                kiwiSaverInfoView.shouldBeVisible();
                kiwiSaverInfoView.getClickHereToGetStartedButton().click();
                break;
            default:
                throw new IllegalArgumentException(String.format("'%s' is not a valid navigation target for this menu", targetMenuItem));
        }
    }

}
