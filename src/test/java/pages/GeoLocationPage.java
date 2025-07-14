package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GeoLocationPage {
    private AndroidDriver driver;
    private final By permissionDialog = AppiumBy.id("com.android.permissioncontroller:id/permission_message");
    private final By geoLocationHeader = AppiumBy.xpath("//android.widget.TextView[contains(@text, 'GEOLOCATION')]");
    private final By allowWhileUsingAppBtn = AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private final By allowOnlyThisTimeBtn = AppiumBy.id("com.android.permissioncontroller:id/permission_allow_one_time_button");
    private final By denyBtn = AppiumBy.id("com.android.permissioncontroller:id/permission_deny_button");
    private final By locationAccuracyDialog = AppiumBy.id("com.google.android.gms:id/message");
    private final By noThanksButton = AppiumBy.id("android:id/button2");
    private final By turnOnButton = AppiumBy.id("android:id/button1");
    private final By geoLocationInfoText = AppiumBy.xpath("//android.widget.TextView[@text='Below you will find the latitude and longitude. You can use Appium to change them with this link.']");

    public GeoLocationPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isPermissionDialogDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(permissionDialog));
            return dialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGeoLocationPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(geoLocationHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void allowLocationWhileUsingApp() {
        driver.findElement(allowWhileUsingAppBtn).click();
    }

    public void allowLocationOnlyThisTime() {
        driver.findElement(allowOnlyThisTimeBtn).click();
    }

    public void denyLocationPermission() {
        driver.findElement(denyBtn).click();
    }

    public boolean isLocationAccuracyDialogDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(locationAccuracyDialog));
            return dialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void tapNoThanksOnLocationAccuracy() {
        driver.findElement(noThanksButton).click();
    }

    public void tapTurnOnLocationAccuracy() {
        driver.findElement(turnOnButton).click();
    }

    public boolean isGeoLocationInfoDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement info = wait.until(ExpectedConditions.visibilityOfElementLocated(geoLocationInfoText));
            return info.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
} 