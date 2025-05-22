package pageObjects.VesselHMM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.VesselSpareRequisitionUI;

public class VesselSpareRequisitionObject extends LeftMenuPageObject {
    private WebDriver driver;
    public String breadcumbTitle = "Spare Parts";

    public VesselSpareRequisitionObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void waitForBreadcumbTitleVisible() {
        waitForElementVisible(driver, VesselSpareRequisitionUI.BREADCRUMB_TITLE);
    }
    public String getBreadcumbTitle() {
        return getTextElement(driver, VesselSpareRequisitionUI.BREADCRUMB_TITLE_SPARE_PART);
    }


}
