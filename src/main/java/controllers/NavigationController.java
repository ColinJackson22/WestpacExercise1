package controllers;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import views.KiwiSaverInfoView;
import views.KiwiSaverRetirementCalculatorView;

public class NavigationController {

    private static Category log = Logger.getLogger(NavigationController.class);

    private MenuController menuController = new MenuController();
    private KiwiSaverInfoView kiwiSaverInfoView = new KiwiSaverInfoView();
    private KiwiSaverRetirementCalculatorView kiwiSaverRetirementCalculatorView = new KiwiSaverRetirementCalculatorView();

    public void navigateTo(String target) {

        // TODO: Add new target locations as required
        switch(target.toLowerCase().replaceAll(" ", "").replaceAll(",", "")){
            case "kiwsaverretirementcalculator":
                log.info("Navigating to the KiwiSaver Retirement Calculator page");
                menuController.bannerSubMenuSelector("KiwiSaver");
                kiwiSaverInfoView.getClickHereToGetStartedButton().click();
                kiwiSaverRetirementCalculatorView.shouldBeVisible();
                break;
            default:
                throw new IllegalArgumentException(String.format("'%s' is not a valid navigation target", target));
        }
    }


}
