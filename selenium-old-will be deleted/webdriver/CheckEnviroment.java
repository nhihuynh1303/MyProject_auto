package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckEnviroment {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Url() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
