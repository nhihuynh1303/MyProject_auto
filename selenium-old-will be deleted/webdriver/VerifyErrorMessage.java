package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyErrorMessage {
    By ErrorMessageBE = By.xpath("//div[@class='el-notification__content']");
    // text = 'Unknown error occurred. Please contact the administrator.'

    // Hàm kiểm tra và chờ cho đến khi element invisible
//    public static void waitUntilInvisible(WebDriver driver, By locator, int timeoutInSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//
//        try {
//            // Kiểm tra nếu element đang visible
//            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//            if (element.isDisplayed()) {
//                System.out.println("Element is visible. Waiting for it to become invisible...");
//                // Chờ cho đến khi element invisible
//                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//                System.out.println("Element is now invisible.");
//            }
//        } catch (Exception e) {
//            System.out.println("Element is already invisible or timeout reached.");
//        }
//    }
//
//    // Hàm thực hiện bước tiếp theo
//    public static void nextStep() {
//        System.out.println("Performing the next step...");
//    }
}
