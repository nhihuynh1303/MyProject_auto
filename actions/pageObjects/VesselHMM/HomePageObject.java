package pageObjects.VesselHMM;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;
import pageUIs.HomePageUI;
import pageUIs.LeftMenuPageUI;

public class HomePageObject extends LeftMenuPageObject {
    private WebDriver driver;
    public HomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String vesselNameHHBN = "HMM BANGKOK";


    public void waitForLoadingIconInvisible() {
        waitUntilInvisible_HMM(driver, HomePageUI.LOADING_ICON);
    }

}
