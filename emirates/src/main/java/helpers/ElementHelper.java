package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static core.BaseClass.webDriver;

public class ElementHelper {
    private static WebDriverWait wait;
    public static final Duration TIME_OUT_IN_SECONDS = Duration.ofSeconds(60);

    public static WebElement elementToBeVisible(WebElement webElement) {
        wait = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static WebElement elementToBeClickable(WebElement webElement) {
        wait = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void moveToElement(WebElement webElement) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).perform();
    }
    public static void scrollToElement(WebElement webElement) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(false);", webElement);
    }
    public static void waitTime(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
