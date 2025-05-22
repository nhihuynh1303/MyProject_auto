package com.VesselHMM;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
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

public class VesselSpareRequisition_implementCheckUnDisplayed extends BaseTest {
    private WebDriver driver;
    private WebDriverWait explicitWait;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private VesselSpareRequisitionObject vesselSpareRequisitionPage;


    @Parameters({})
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get("");
        driver.manage().window().maximize();

        //loginPage = new LoginPageObject(driver);
        // 05 May - after apply page Generator:
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
        //Assert.assertEquals(homePage.getVesselWelcomeHeader(driver), homePage.vesselNameHHBN);
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
