package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class BaseTest {
    private WebDriver driver;
    private WebDriverWait explicitWait;
    protected int generateRandomNumber(){
        return new Random().nextInt(99999);
    }

    protected WebDriver getBrowserDriver(String browserName){
        switch (browserName){
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get("");
        driver.manage().window().maximize();
        return driver;
    }

    // switch site URL
    protected WebDriver getBrowserDriver(String browserName, String url){
        switch (browserName){
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        //driver.get(url);
        driver.get(url);
        driver.manage().window().maximize();
        return driver;
    }
    protected void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getCurrentDate() {
        // get current date
        LocalDate currentDate = LocalDate.now();

        // format date YYYY-MM-DD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }
}
