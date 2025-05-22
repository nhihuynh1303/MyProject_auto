package pageObjects.VesselHMM;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;
import pageUIs.LeftMenuPageUI;

public class LeftMenuPageObject extends BasePage {
    // page trung gian
    WebDriver driver;
    public LeftMenuPageObject(WebDriver driver){
        this.driver = driver;
    }

    // ========== SWITCH PAGE OBJECT ======
    // Use with BasePageUI class
    public boolean isLeftMenuDisplayed() {
        waitForElementVisible(driver, LeftMenuPageUI.LEFT_MENU);
        return isElementDisplayed(driver, LeftMenuPageUI.LEFT_MENU);
    }
    public void waitForCategoryHomeMainVisible() {
        waitForElementVisible(driver, LeftMenuPageUI.CATEGORY_HOME_MAIN);
    }

    public void openProcurementMenu() {
        clickToElement(driver, LeftMenuPageUI.PROCUREMENT_MENU);
    }


    public void openRequisitionMenu() {
        clickToElement(driver, LeftMenuPageUI.REQUISITION_MENU);
    }
    public VesselSpareRequisitionObject openSpareRequisitionMenu() {
        clickToElement(driver, LeftMenuPageUI.SPARE_REQUISITION_MENU);
        return PageGeneratorManager.getVesselSpareRequisitionPage(driver);
    }
}
