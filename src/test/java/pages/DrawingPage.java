package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DrawingPage {
    private AndroidDriver driver;
    private final By drawingHeader = AppiumBy.xpath("//android.widget.TextView[contains(@text, 'DRAWING')]");
    private final By canvas = AppiumBy.xpath("//android.view.View[contains(@content-desc, 'drawing-canvas')]");
    private final By clearBtn = AppiumBy.accessibilityId("test-CLEAR");
    private final By saveBtn = AppiumBy.accessibilityId("test-SAVE");
    private final By image = AppiumBy.xpath("//android.widget.Image");

    public DrawingPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isDrawingPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(drawingHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

} 