package pageObjects.VesselHMM;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String user = "";
    public String pw = "";

    public void waitForLoginVisible() {
        waitForElementVisible(driver, LoginPageUI.LOGIN_TITLE);
    }
    public void inputUserName(String userName) {
        sendKeyToElement(driver, LoginPageUI.USER_ID, userName);
    }

    public void inputPassword(String passWord) {
        sendKeyToElement(driver, LoginPageUI.USER_PASSWORD, passWord);
    }

    // click login button will navigate to Homepage Object
    public HomePageObject clickLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getHomePage(driver);
    }
}
