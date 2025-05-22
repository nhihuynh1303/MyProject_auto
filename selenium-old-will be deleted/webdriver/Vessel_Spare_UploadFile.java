package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Vessel_Spare_UploadFile {
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

    // locator for Spare requisition
    By ProcurementMenu = By.xpath("//span[@class='text-xs mt-1 text-center' and text()='Procurement']/parent::div/parent::div");
    By RequisitionMenu = By.xpath("//div[@class='flex-1']//div[text()='Requisition']");
    By SpareRequisitionMenu = By.xpath("//div[@class='flex-1']//div[text()='Requisition']/parent::div/parent::div/following-sibling::div//a[text()='Spare Parts']");
    By BreadcrumbTitle = By.xpath("//div[@class='breadcrumb-title']");
    By BreadcrumbTitleSpareParts = By.xpath("//div[@class='breadcrumb-title']/span[position()=1]");

    By CategoryHomeMain = By.xpath("//div[text()='Home']/parent::div//following-sibling::div[contains(@class,'gap-3')]");
    By LoadingIcon = By.xpath("//div/img[@class='logo']");
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("");
        driver.manage().window().maximize();
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
    public void TC_03_OpenSpareRequisitionPage() {
        //sleepInSecond(3);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(CategoryHomeMain));
        explicitWait.until(ExpectedConditions.elementToBeClickable(ProcurementMenu));
        driver.findElement(ProcurementMenu).click();
        explicitWait.until(ExpectedConditions.elementToBeClickable(RequisitionMenu));
        driver.findElement(RequisitionMenu).click();
        explicitWait.until(ExpectedConditions.elementToBeClickable(SpareRequisitionMenu));
        driver.findElement(SpareRequisitionMenu).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(BreadcrumbTitle));
        Assert.assertEquals(driver.findElement(BreadcrumbTitleSpareParts).getText(), "Spare Parts");
    }

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
    public void TC_04_OpenRequisitionRemark() {
        explicitWait.until(ExpectedConditions.elementToBeClickable(RequisitionRemarkLocator)).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(RequisitionRemarkPopupTitleLocator));
        System.out.println("Open Requisition remark");
    }

    @Test
    public void TC_05_InputTextRequisitionRemark() {
        driver.findElement(RequisitionRemark_TextAreaLocator).clear();
        driver.findElement(RequisitionRemark_TextAreaLocator).sendKeys("This is Requisition remark");
    }

    @Test
    public void TC_06_AttachFileRequisitionRemark() {
        System.out.println("File locator: "+ filePath);
        driver.findElement(AttachFileLocator).sendKeys( filePath);
        //driver.findElement(uploadBy).sendKeys(hcmFilePath + "\n" + hnFilePath + "\n" + dnFilePath);
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(UploadFileView_FileNameLocator).isDisplayed());
        waitUntilInvisible(UploadFileView_LoadingIconLocator);
        driver.findElement(RequisitionRemark_OKButtonLocator).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(RequisitionRemarkPopupTitleLocator));
    }

    //open requisition remark again to verify file is updated
    @Test
    public void TC_07_AttachFileRequisitionRemark() {
        driver.findElement(RequisitionRemarkLocator).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(RequisitionRemarkPopupTitleLocator));
        Assert.assertTrue(driver.findElement(UploadFileView_FileNameLocator).isDisplayed());

        driver.findElement(RequisitionRemark_CloseButtonLocator).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(RequisitionRemarkPopupTitleLocator));
    }

    By SparePartSelectorButton = By.xpath("//span[text()='Spare Part Selector']/parent::button");

    @Test
    public void TC_08_OpenSparePartSelector() {
        //waitUntilInvisible(LoadingIcon);
        explicitWait.until(ExpectedConditions.elementToBeClickable(SparePartSelectorButton));
        driver.findElement(SparePartSelectorButton).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(LoadingIcon));
    }

    By GroupSelectBox = By.xpath("//div[contains(@class,'placeholder')]/span[text()='Group Name']");
    By GroupList = By.xpath("//ul[@id='el-id-4031-133']/li");
    By GroupItem201 = By.xpath("");
    @Test
    public void TC_09_SelectGroup() {
        selectedDropdownList("//div[contains(@class,'placeholder')]/span[text()='Group Name']",
                "//ul[contains(@class, 'el-select-dropdown__list')]/li",
                "201 - Main Engine");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Group']/following-sibling::td//div[contains(@class,'placeholder')]/span")).
                getText(),"201 - Main Engine");
    }

    By MachineryTest = By.xpath("//div[@data-key='201-DME']//span[text()='MAIN ENGINE(DIESEL)']");
    By EquipmentTest = By.xpath("//div[@data-key='201-DME-AVD']//span[text()='AXIAL VIBRATION DAMPER']");

    By MachineryTestIsSelected = By.xpath("//td[text()='Machinery']/following-sibling::td//div[contains(@class,'placeholder')]/span");
    By EquipmentTestIsSelected = By.xpath("//td[text()='Equipment']/following-sibling::td//div[contains(@class,'placeholder')]/span");
    @Test
    public void TC_10_SelectMachineryEquipment() {
        explicitWait.until(ExpectedConditions.elementToBeClickable(MachineryTest));
        System.out.println("Check Machinery locator " + driver.findElement(MachineryTest).isDisplayed());
        driver.findElement(MachineryTest).click();

        explicitWait.until(ExpectedConditions.elementToBeClickable(EquipmentTest));
        System.out.println("Check Equipment locator " + driver.findElement(EquipmentTest).isDisplayed());
        driver.findElement(EquipmentTest).click();

        waitUntilInvisible(LoadingIcon);
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(LoadingIcon));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(MachineryTestIsSelected));
        Assert.assertEquals(driver.findElement(MachineryTestIsSelected).getText(),"DME - MAIN ENGINE(DIESEL)");
        System.out.println("MachineryTestIsSelected: "+ driver.findElement(MachineryTestIsSelected).getText());

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentTestIsSelected));
        Assert.assertEquals(driver.findElement(EquipmentTestIsSelected).getText(),"AVD - AXIAL VIBRATION DAMPER");
        System.out.println("EquipmentTestIsSelected: "+ driver.findElement(EquipmentTestIsSelected).getText());
    }
    By DMEAVD000029ItemCheckbox = By.xpath("//div[text()='DMEAVD000029']/parent::div/parent::div/parent::td/preceding-sibling::td");
    By OK_Button_SparePartSelector = By.xpath("//span[@class='el-dialog__title' and text()='Spare Part Selector']/parent::header//following-sibling::footer//button/span[text()='OK']");
    By SparePartSelectorPopup = By.xpath("//span[@class='el-dialog__title' and text()='Spare Part Selector']");
    @Test
    public void TC_11_ItemIsSelected() {
        // check a checkbox
        WebElement element = driver.findElement(DMEAVD000029ItemCheckbox);

        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(DMEAVD000029ItemCheckbox));
        element.click();

        // click on OK button
        driver.findElement(OK_Button_SparePartSelector).click();

        // Verify SparePartSelector popup is Closed
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(SparePartSelectorPopup));
    }

    By MaterialCode_ItemList = By.xpath("//tr[@class='el-table__row accent-text']//td[contains(@class,'el-table_1_column_2')]//div[contains(@class,'text-center ')]/div");
    By DrawingNo_ItemList = By.xpath("//tr[@class='el-table__row accent-text']//td[contains(@class,'el-table_1_column_3')]//div[contains(@class,'text-center ')]/div");
    @Test
    public void TC_12_ItemIsSelected0nItemList() {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(MaterialCode_ItemList));
        System.out.println("TC08- MaterialCode_ItemList: "+ driver.findElement(MaterialCode_ItemList).getText());
        System.out.println("TC08 - DrawingNo_ItemList: "+ driver.findElement(DrawingNo_ItemList).getText());
        Assert.assertEquals(driver.findElement(MaterialCode_ItemList).getText(),"DMEAVD000029");
        Assert.assertEquals(driver.findElement(DrawingNo_ItemList).getText(),"A13-166018-0");
    }

    By ScrollBarHorizontal = By.xpath("//tr[@class='el-table__row accent-text']/ancestor::div[contains(@class,'el-scrollbar__wrap')]/following-sibling::div[@class='el-scrollbar__bar is-horizontal']");
    By RequestColumnLocator =
            By.xpath("//td[contains(@class,'el-table_1_column_8')]//div[contains(@class,'text-center ')]//input");

    By AssessReasonColumnLocator =
            By.xpath("//td[contains(@class,'el-table_1_column_19')]//div[contains(@class,'text-center ')]/div");
    By ItemRemarkColumnLocator =
            By.xpath("//td[contains(@class,'el-table_1_column_20')]//div[contains(@class,'text-center ')]/div");

    By SaveButtonLocator = By.xpath("//span[text()='Save']");
    @Test
    public void TC_13_InputRequest() {
        WebElement RequestColumn = driver.findElement(RequestColumnLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", RequestColumn);
        explicitWait.until(ExpectedConditions.elementToBeClickable(RequestColumnLocator));

        RequestColumn.sendKeys("1");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(SaveButtonLocator));
    }

    By NeedDateLocator = By.xpath("//td[text()=' Need Date']/following-sibling::td//input[@placeholder='Pick a date']");
    @Test
    public void TC_14_InputNeedDate() {
        String formattedDate = getCurrentDate();
        System.out.println("Current Date: " + formattedDate);
        driver.findElement(NeedDateLocator).sendKeys(formattedDate);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(SaveButtonLocator));
    }

    By VoyageInputLocator = By.xpath("//td[contains(text(),'Voyage')]/following-sibling::td[position()=1]//input");
    @Test
    public void TC_15_InputVoyage() {
        driver.findElement(VoyageInputLocator).clear();
        driver.findElement(VoyageInputLocator).sendKeys("0029W");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(SaveButtonLocator));
    }
    By SaveSuccessToast = By.cssSelector("div#notification_1 p");
    @Test
    public void TC_16_ClickOnSaveButton() {
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
    public void TC_17_ClickOnSubmitButton_OpenSubmitPopup() {
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
        System.out.println("TC_13_ClickOnSubmitButton_OpenSubmitPopup - Doc No auto Approved: "+docNo);
        waitUntilInvisible(SubmitSuccessToast);
    }

    @Test
    public void TC_18_AttachFileRequisitionRemark() {
        driver.findElement(RequisitionRemarkLocator).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(RequisitionRemarkPopupTitleLocator));
        Assert.assertTrue(driver.findElement(UploadFileView_FileNameLocator).isDisplayed());
        driver.findElement(RequisitionRemark_CloseButtonLocator).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(RequisitionRemarkPopupTitleLocator));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
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
    public void selectedDropdownList(String parentXpath, String childXpath, String itemTextExpected) {
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
