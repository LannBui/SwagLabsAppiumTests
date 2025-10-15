package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetailsPage {
    private AndroidDriver driver;

    public ProductDetailsPage (AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Try scrolling by content-desc first
        String byDescScroll = "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().description(\"test-ADD TO CART\"))";
        try { driver.findElement(AppiumBy.androidUIAutomator(byDescScroll)); } catch (Exception ignored) {}

        // Fallback scroll by visible text (covers variations)
        String byTextScroll = "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().textContains(\"ADD TO CART\"))";
        try { driver.findElement(AppiumBy.androidUIAutomator(byTextScroll)); } catch (Exception ignored) {}

        By byAccId = AppiumBy.accessibilityId("test-ADD TO CART");
        By byText = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"ADD TO CART\")");

        WebElement button = null;
        try {
            button = wait.until(ExpectedConditions.presenceOfElementLocated(byAccId));
        } catch (Exception ignored) {}
        if (button == null) {
            button = wait.until(ExpectedConditions.presenceOfElementLocated(byText));
        }
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void navigateBackToProductList() {
        driver.navigate().back();
    }
}
