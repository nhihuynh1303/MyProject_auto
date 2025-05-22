package commons;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public void openPageURL(WebDriver driver, String url){
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver){
         return driver.getTitle();
    }
    public String getPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public Alert waitAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver){
        waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver){
        waitAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver){
        return waitAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String keysToSend){
        waitAlertPresence(driver).sendKeys(keysToSend);
    }
    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
    public WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }


    public List<WebElement> getListElements(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }
    public void clickToElement(WebDriver driver, String locator){
        getElement(driver, locator).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keysToSend){
        getElement(driver, locator).sendKeys(keysToSend);
    }

    public void selectItemInDropDown(WebDriver driver, String locator, String textItem){
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public String getSelectedItemInDropDown(WebDriver driver, String locator){
        return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
    }
    public boolean isDropDownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

     public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(2);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

        sleepInSecond(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName){
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getTextElement(WebDriver driver, String locator){
        return getElement(driver, locator).getText();
    }

    public String getCSSValue(WebDriver driver, String locator, String propertyName){
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorByRGBAColor(String rgbaValue){
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator){
        return getListElements(driver, locator).size();
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator){
        if(!getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }

    public void unCheckToCheckbox(WebDriver driver, String locator){
        if(getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getElement(driver, locator).isEnabled();
    }
    public boolean isElementSelected(WebDriver driver, String locator){
        return getElement(driver, locator).isSelected();
    }

    public void switchToIframe(WebDriver driver, String locator){
        driver.switchTo().frame(getElement(driver, locator));
    }
    public void switchToDefaultPage(WebDriver driver, String locator){
        driver.switchTo().defaultContent();
    }

    public void holdToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }
    public void clickAndHoldToElement(WebDriver driver, String locator){
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }
    public void releaseLeftMouse(WebDriver driver){
        new Actions(driver).release()   ;
    }
    public void doubleClickToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    public void clickToElementByAction(WebDriver driver, String locator){
        new Actions(driver).click(getElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator ){
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys keys){
        new Actions(driver).sendKeys(getElement(driver, locator), keys).perform();
    }
    public void scrollToElement(WebDriver driver, String locator){
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }
    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(driver, locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementPresence(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    //================== ONLY FOR HMM

    // old code
//    public void waitUntilInvisible_HMM(WebDriver driver, String locator){
//        try {
//            waitForElementVisible(driver, locator);
//            if (isElementDisplayed(driver, locator)) {
//                System.out.println(locator + "Element is visible. Waiting for it to become invisible...");
//                // Wait for element invisible
//                waitForElementInVisible(driver,locator);
//                System.out.println(locator + " - Element is now invisible.");
//            }
//        } catch (Exception e) {
//            System.out.println(locator + " - Element is already invisible or timeout reached or cannot find locator");
//        }
//    }


    // code updated after implement check undisplayed
    public void waitUntilInvisible_HMM(WebDriver driver, String locator){
        boolean result = isElementUndisplayed(driver, locator);

        if (result) {
            System.out.println("Element is already undisplayed");
        } else {
            System.out.println("Element is displayed, waiting for it to become invisible");
            waitForElementInVisible(driver, locator);
            System.out.println("Element is now invisible");
        }

        System.out.println("End time = " + new Date().toString());
    }

    public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        System.out.println("Start time = " + new Date().toString());
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListElements(driver, locator);
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

        if (elements.size() == 0) {
            System.out.println("Element not in DOM");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element in DOM but not visible/displayed");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else {
            System.out.println("Element in DOM and visible");
            return false;
        }
    }

    public void selectedDropdownListHMM(WebDriver driver, String parentLocator, String childLocator, String itemTextExpected) {
        clickToElement(driver, parentLocator);
        List<WebElement> allItems = getListElements(driver, childLocator);
        waitForElementPresence(driver, childLocator);
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

    //====== HMM project - only
    public String getVesselWelcomeHeader(WebDriver driver) {
        return getTextElement(driver, BasePageUI.VESSEL_NAME_HEADER);
    }

    public void waitForWelcomeAccountHeaderVisible(WebDriver driver) {
        waitForElementVisible(driver, BasePageUI.WELCOME_ACCOUNT_HEADER);
    }
    //==========
    //    public By getByXpath(String locator){
    //        return By.xpath(locator);
    //    }

    //===== Refactor By Locator/ Global Constants Class function
    private By getByLocator(String prefixLocator) {
        By by = null;
        if (prefixLocator.toUpperCase().startsWith("ID")) {
            by = By.id(prefixLocator.substring(3));
        } else if (prefixLocator.toUpperCase().startsWith("CLASS")) {
            by = By.className(prefixLocator.substring(6));
        } else if (prefixLocator.toUpperCase().startsWith("NAME")) {
            by = By.name(prefixLocator.substring(5));
        } else if (prefixLocator.toUpperCase().startsWith("TAGNAME")) {
            by = By.tagName(prefixLocator.substring(8));
        } else if (prefixLocator.toUpperCase().startsWith("CSS")) {
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (prefixLocator.toUpperCase().startsWith("XPATH")) {
            by = By.xpath(prefixLocator.substring(6));
        } else {
            throw new RuntimeException("Locator type is not support!!!!");
        }

        return by;
    }
}
