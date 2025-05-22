package com.VesselHMM;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.VesselHMM.HomePageObject;
import pageObjects.VesselHMM.LoginPageObject;
import pageObjects.VesselHMM.VesselSpareRequisitionObject;

import java.time.Duration;

public class MultipleBrowser_VesselSpareRequisition extends BaseTest {
    private WebDriver driver;

    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private VesselSpareRequisitionObject vesselSpareRequisitionPage;

    private String userUrl, adminUrl;

    @Parameters({"browser", "userUrl", "adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String userUrl, String adminUrl) {
        this.userUrl = userUrl;
        this.adminUrl = adminUrl;

        driver = getBrowserDriver(browserName, this.userUrl);

        loginPage = PageGeneratorManager.getLoginPage(driver);
    }

    @Test
    public void Vessel_TC_01_LoginPage() {
        loginPage.waitForLoginVisible();
        loginPage.inputUserName(loginPage.user);
        loginPage.inputPassword(loginPage.pw);

        homePage = loginPage.clickLoginButton(); //  homePage = new HomePageObject(driver);
        homePage.waitForWelcomeAccountHeaderVisible(driver);
        Assert.assertTrue(homePage.isLeftMenuDisplayed());
        Assert.assertEquals(homePage.getVesselWelcomeHeader(driver), homePage.vesselNameHHBN);
        //sleepInSecond(3);
        homePage.waitForLoadingIconInvisible();
    }

    @Test
    public void Vessel_TC_02_OpenSpareRequisitionPage() {
        homePage.waitForCategoryHomeMainVisible();
        homePage.openProcurementMenu();
        homePage.openRequisitionMenu();
        vesselSpareRequisitionPage = homePage.openSpareRequisitionMenu(); //vesselSpareRequisitionPage = new VesselSpareRequisitionObject(driver);
        vesselSpareRequisitionPage.waitForBreadcumbTitleVisible();
        Assert.assertEquals(vesselSpareRequisitionPage.getBreadcumbTitle(),vesselSpareRequisitionPage.breadcumbTitle);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
