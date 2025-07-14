package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WebViewPage {
    private AndroidDriver driver;
    private final By webViewTitle = AppiumBy.xpath("//android.widget.TextView[@text='WEBVIEW SELECTION']");

    public WebViewPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isWebViewDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(webViewTitle));
            return title.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
} 