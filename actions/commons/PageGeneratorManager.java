package commons;

import com.VesselHMM.VesselSpareRequisition;
import org.openqa.selenium.WebDriver;
import pageObjects.VesselHMM.HomePageObject;
import pageObjects.VesselHMM.LoginPageObject;
import pageObjects.VesselHMM.VesselSpareRequisitionObject;

public class PageGeneratorManager {
    public static LoginPageObject getLoginPage (WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static HomePageObject getHomePage (WebDriver driver){
        return new HomePageObject(driver);
    }

    public static VesselSpareRequisitionObject getVesselSpareRequisitionPage (WebDriver driver){
        return new VesselSpareRequisitionObject(driver);
    }
}
