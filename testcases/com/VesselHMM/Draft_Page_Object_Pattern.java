package com.VesselHMM;


import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Draft_Page_Object_Pattern extends BasePage {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("");

        driver.manage().window().maximize();
    }
    WebDriverWait explicitWait;
    By LoginScreen = By.xpath("//div[@class='el-form-item__content']//h1[text()='Login']");
    By UserID = By.xpath("//input[@placeholder='User ID']");
    By UserPassword = By.xpath("//input[@placeholder='User Password']");
    By LoginButton = By.xpath("//button//span[text()='Login']");
    By VesselNameHeader = By.xpath("//div[@class='top-nav']//span");
    By VesselWelcomeHeader = By.xpath("//div[text()='Welcome']");

    String user = ""; // CHA JI HWAN
    String pw = "";
    String vesselNameHHBN = "";

    // locator for Spare requisition
    By ProcurementMenu = By.xpath("//span[@class='text-xs mt-1 text-center' and text()='Procurement']/parent::div/parent::div");
    By RequisitionMenu = By.xpath("//div[@class='flex-1']//div[text()='Requisition']");
    By SpareRequisitionMenu = By.xpath("//div[@class='flex-1']//div[text()='Requisition']/parent::div/parent::div/following-sibling::div//a[text()='Spare Parts']");
    By BreadcrumbTitle = By.xpath("//div[@class='breadcrumb-title']");
    By BreadcrumbTitleSpareParts = By.xpath("//div[@class='breadcrumb-title']/span[position()=1]");

    By CategoryHomeMain = By.xpath("//div[text()='Home']/parent::div//following-sibling::div[contains(@class,'gap-3')]");
    By LoadingIcon = By.xpath("//div/img[@class='logo']");

    @Test
    public void TC_01_LoginPage() {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(LoginScreen));
        sendKeyToElement(driver,"//input[@placeholder='User ID']",user);
        sendKeyToElement(driver,"//input[@placeholder='User Password']",pw);
        clickToElement(driver,"//button//span[text()='Login']");
        waitForElementVisible(driver,"//div[@class='top-nav']//span");
        Assert.assertEquals(driver.findElement(VesselNameHeader).getText(), vesselNameHHBN);
        waitUntilInvisible(LoadingIcon);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    private void waitUntilInvisible(By locatorOfElement ){
        try {
            // Verify if element visible
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locatorOfElement));
            if (driver.findElement(locatorOfElement).isDisplayed()) {
                System.out.println(locatorOfElement + "Element is visible. Waiting for it to become invisible...");
                // Wait for element invisible
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(locatorOfElement));
                System.out.println(locatorOfElement + " - Element is now invisible.");
            }
        } catch (Exception e) {
            System.out.println(locatorOfElement + " - Element is already invisible or timeout reached or cannot find locator");
        }
    }
    private void selectedDropdownList(String parentXpath, String childXpath, String itemTextExpected) {
        driver.findElement(By.xpath(parentXpath)).click();
        List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        System.out.println("Item size: " + allItems.size());

        // get list of all items:
        for (WebElement item : allItems) {
            System.out.println("Get item: " + item.getText());
        }

        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                System.out.println("GroupTestIsSelected " + item.getText());
                item.click();
                break;
            }
        }

    }
}
