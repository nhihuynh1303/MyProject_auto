package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Vessel_Oil_UploadFile {
    WebDriver driver;
    WebDriverWait explicitWait;

    By LoginScreen = By.xpath("//div[@class='el-form-item__content']//h1[text()='Login']");
    By UserID = By.xpath("//input[@placeholder='User ID']");
    By UserPassword = By.xpath("//input[@placeholder='User Password']");
    By LoginButton = By.xpath("//button//span[text()='Login']");
    By VesselNameHeader = By.xpath("//div[@class='top-nav']//span");
    By VesselWelcomeHeader = By.xpath("//div[text()='Welcome']");

    String user = ""; // CHA JI HWAN
    String pw = "123";
    String vesselNameHHBN = "";

    // locator for Store requisition
    By ProcurementMenu = By.xpath("//span[@class='text-xs mt-1 text-center' and text()='Procurement']/parent::div/parent::div");
    By RequisitionMenu = By.xpath("//div[@class='flex-1']//div[text()='Requisition']");
    By OilRequisitionMenu = By.xpath("//div[@class='flex-1']//div[text()='Requisition']/parent::div/parent::div/following-sibling::div//a[text()=\"Ship's Oils\"]");
    By BreadcrumbTitle = By.xpath("//div[@class='breadcrumb-title']");
    By BreadcrumbTitleStore = By.xpath("//div[@class='breadcrumb-title']/span[position()=1]");

    By CategoryHomeMain = By.xpath("//div[text()='Home']/parent::div//following-sibling::div[contains(@class,'gap-3')]");
    By LoadingIcon = By.xpath("//div/img[@class='logo']");

    // run from XML file
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        if(browserName.equals("Firefox")){
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("");
        driver.manage().window().maximize();
    }

//    @BeforeClass
//    public void beforeClass() {
//        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        driver.get("");
//        //driver.get("");
//        driver.manage().window().maximize();
//    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    @Test
    public void TC_01_LoginPage() {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(LoginScreen));
    }

    @Test
    public void TC_02_Login() {
        driver.findElement(UserID).sendKeys(user);
        driver.findElement(UserPassword).sendKeys(pw);
        driver.findElement(LoginButton).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(VesselWelcomeHeader));
        Assert.assertEquals(driver.findElement(VesselNameHeader).getText(), vesselNameHHBN);

        waitUntilInvisible(LoadingIcon);
    }

    @Test
    public void TC_03_OpenOilRequisitionPage() {
        //sleepInSecond(3);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(CategoryHomeMain));
        explicitWait.until(ExpectedConditions.elementToBeClickable(ProcurementMenu)).click();
        explicitWait.until(ExpectedConditions.elementToBeClickable(RequisitionMenu)).click();
        explicitWait.until(ExpectedConditions.elementToBeClickable(OilRequisitionMenu)).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(BreadcrumbTitle));
        Assert.assertEquals(driver.findElement(BreadcrumbTitleStore).getText(), "Ship's Oils");
    }
    By OilSelectorButtonLocator = By.xpath("//span[text()='Oil Selector']/parent::button");

    @Test
    public void TC_04_OpenOilSelector() {
        waitUntilInvisible(LoadingIcon);
        explicitWait.until(ExpectedConditions.elementToBeClickable(OilSelectorButtonLocator)).click();
        waitUntilInvisible(LoadingIcon);
    }

    By F000671ItemCheckbox = By.xpath("//div[text()='FO00671']/ancestor::tr//td[position()=1]");
    By OK_Button_OilSelector = By.xpath("//span[@class='el-dialog__title' and text()='Oil Selector']/parent::header//following-sibling::footer//button/span[text()='OK']");
    By OilSelectorPopup = By.xpath("//span[@class='el-dialog__title' and text()='Oil Selector']");
    @Test
    public void TC_05_ItemIsSelected() {
        // check a checkbox
        WebElement element = driver.findElement(F000671ItemCheckbox);

        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(F000671ItemCheckbox));
        element.click();

        // click on OK button
        driver.findElement(OK_Button_OilSelector).click();

        // Verify SparePartSelector popup is Closed
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(StoreSelectorPopup));
        waitUntilInvisible(OilSelectorPopup);
    }

    By RequestColumnLocator =
            By.xpath("//td[contains(@class,'el-table_1_column_13')]//input");
    By SaveButtonLocator = By.xpath("//span[text()='Save']");
    @Test
    public void TC_06_InputRequest() {
        WebElement RequestColumn = driver.findElement(RequestColumnLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", RequestColumn);
        explicitWait.until(ExpectedConditions.elementToBeClickable(RequestColumnLocator));

        RequestColumn.sendKeys("1");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(SaveButtonLocator));
    }

    By NeedDateLocator = By.xpath("//p[text()=' Need Date']/ancestor::td/following-sibling::td//input[@placeholder='Pick a date']");
    @Test
    public void TC_07_InputNeedDate() {
        String formattedDate = getCurrentDate();
        System.out.println("Current Date: " + formattedDate);
        driver.findElement(NeedDateLocator).sendKeys(formattedDate);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(SaveButtonLocator));
    }

    By VoyageInputLocator = By.xpath("//td[text()='Assess Remark']/preceding-sibling::td[position()=1]//input");

    @Test
    public void TC_08_InputVoyage() {
        driver.findElement(VoyageInputLocator).clear();
        driver.findElement(VoyageInputLocator).sendKeys("0029W");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(SaveButtonLocator));
    }
    By SaveSuccessToast = By.cssSelector("div#notification_1 p");


    By RequisitionRemarkLocator =
            By.xpath("//td[contains(text(),'Requisition Remark')]/following-sibling::td[position()=1]//input");
    //By RequisitionRemark_AttachFileButtonLocator = By.xpath("//button/span[text()='Attach File']");
    By RequisitionRemark_OKButtonLocator =
            By.xpath("//span[text()='Requisition Remark']/parent::header/following-sibling::footer//button/span[text()='OK']");
    By RequisitionRemark_CloseButtonLocator =
            By.xpath("//span[text()='Requisition Remark']/parent::header/following-sibling::footer//button/span[text()='Close']");
    By RequisitionRemark_TextAreaLocator =
            By.xpath("//span[text()='Requisition Remark']/parent::header/following-sibling::div//textarea");
    By AttachFileLocator = By.xpath("//input[@type='file']");
    By RequisitionRemarkPopupTitleLocator = By.xpath("//span[text()='Requisition Remark']");

    By UploadFileView_LoadingIconLocator = By.xpath("//div[contains(@class,'upload-file-view')]//button[contains(@class,'is-loading')]");
    String projectPath = System.getProperty("user.dir");
    String fileName = "image.png";
    String filePath = projectPath+ File.separator+"uploadFiles"+File.separator+fileName;

    By UploadFileView_FileNameLocator = By.xpath("//div[contains(@class,'upload-file-view')]//span[text()='"+fileName+"']");

    @Test
    public void TC_09_OpenRequisitionRemark() {
        explicitWait.until(ExpectedConditions.elementToBeClickable(RequisitionRemarkLocator)).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(RequisitionRemarkPopupTitleLocator));
        System.out.println("Open Requisition remark");
    }

    @Test
    public void TC_10_InputTextRequisitionRemark() {
        driver.findElement(RequisitionRemark_TextAreaLocator).clear();
        driver.findElement(RequisitionRemark_TextAreaLocator).sendKeys("This is Requisition remark");
    }

    @Test
    public void TC_11_AttachFileRequisitionRemark() {
        System.out.println("File locator: "+ filePath);
        driver.findElement(AttachFileLocator).sendKeys(filePath);
        //driver.findElement(uploadBy).sendKeys(hcmFilePath + "\n" + hnFilePath + "\n" + dnFilePath);
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(UploadFileView_FileNameLocator).isDisplayed());
        waitUntilInvisible(UploadFileView_LoadingIconLocator);
        driver.findElement(RequisitionRemark_OKButtonLocator).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(RequisitionRemarkPopupTitleLocator));
    }

    //open requisition remark again to verify file is updated
    @Test
    public void TC_12_AttachFileRequisitionRemark() {
        driver.findElement(RequisitionRemarkLocator).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(RequisitionRemarkPopupTitleLocator));
        Assert.assertTrue(driver.findElement(UploadFileView_FileNameLocator).isDisplayed());

        driver.findElement(RequisitionRemark_CloseButtonLocator).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(RequisitionRemarkPopupTitleLocator));
    }


    @Test
    public void TC_13_ClickOnSaveButton() {
        explicitWait.until(ExpectedConditions.elementToBeClickable(SaveButtonLocator)).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(LoadingIcon));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(SaveSuccessToast));
        Assert.assertEquals(driver.findElement(SaveSuccessToast).getText(), "It has been saved normally.");

        String docNo = driver.findElement(By.xpath("//td[contains(text(),'Document No')]/following-sibling::td[position()=1]//input")).getAttribute("value");
        System.out.println("TC_ClickOnSaveButton Doc No is Saved: "+docNo);
    }
    By SubmitButtonLocator = By.xpath("//div[contains(@class,'gap-1')]//span[text()='Submit']");
    By SubmitPopupLocator = By.xpath("//header[contains(@class,'el-dialog__header')]//span[text()='Submit']");
    By ApprovalAccountCE = By.xpath("//div[text()=' C/E (국동교 / KUK DONGKYO)']");
    By SubmitButtonOnPopupLocator = By.xpath("//span[text()='Submit']/parent::header[contains(@class,'el-dialog__header')]/following-sibling::div//span[text()='Submit']");
    By SubmitSuccessToast = By.xpath("//div[@class='el-notification__content']/p");

    @Test
    public void TC_14_ClickOnSubmitButton_OpenSubmitPopup() {
        waitUntilInvisible(SaveSuccessToast);
        explicitWait.until(ExpectedConditions.elementToBeClickable(SubmitButtonLocator));
        driver.findElement(SubmitButtonLocator).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(SubmitPopupLocator));

        Actions actions = new Actions(driver);

        // Perform double-click on the element - Remove CE account - auto approval
        //actions.doubleClick(driver.findElement(ApprovalAccountCE)).perform();
       // explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(ApprovalAccountCE));

        // click on Submit on Popup
        driver.findElement(SubmitButtonOnPopupLocator).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(SubmitSuccessToast));
        Assert.assertEquals(driver.findElement(SubmitSuccessToast).getText(), "It has been submitted normally.");

        String docNo = driver.findElement(By.xpath("//td[contains(text(),'Document No')]/following-sibling::td[position()=1]//input")).getAttribute("value");
        System.out.println("TC_ClickOnSubmitButton_OpenSubmitPopup - Doc No auto Approved: "+docNo);
        waitUntilInvisible(SubmitSuccessToast);
    }

    public void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCurrentDate() {
        // get current date
        LocalDate currentDate = LocalDate.now();

        // format date YYYY-MM-DD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }
    public void waitUntilInvisible(By locatorOfElement ){
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
}
