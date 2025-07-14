package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class MenuPage {
    AndroidDriver driver;
    public MenuPage (AndroidDriver driver){
        this.driver= driver;
    }

    private final By menuBtn = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup/android.widget.ImageView");
    private final By allItems = AppiumBy.accessibilityId("test-ALL ITEMS");
    private final By webView = AppiumBy.accessibilityId("test-WEBVIEW");
    private final By qrCode = AppiumBy.accessibilityId("test-QR CODE SCANNER");
    private final By geoLoc = AppiumBy.accessibilityId("test-GEO LOCATION");
    private final By drawing = AppiumBy.accessibilityId("test-DRAWING");
    private final By about = AppiumBy.accessibilityId("test-ABOUT");
    private final By logout = AppiumBy.accessibilityId("test-LOGOUT");
    private final By resetApp = AppiumBy.accessibilityId("test-RESET APP STATE");
    private final By camaraPermissionDialog = AppiumBy.id("com.android.permissioncontroller:id/grant_dialog");
    private final By locationPermissionDialog = AppiumBy.id("com.android.permissioncontroller:id/permission_message");


    public void openMenu() {
        driver.findElement(menuBtn).click();
    }
    public void tapAllItem() {
        driver.findElement(allItems).click();
    }
    public void tapWebView() {
        driver.findElement(webView).click();
    }
    public void tapQrCode() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(qrCode).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//*[@text='While using the app']")));

    }
    public void tapGeoLoc() {
        driver.findElement(geoLoc).click();
    }
    public void tapDrawing() {
        driver.findElement(drawing).click();
    }
    public void tapAbout() {
        driver.findElement(about).click();
    }
    public void tapLogout() {
        driver.findElement(logout).click();
    }
    public void tapResetApp() {
        driver.findElement(resetApp).click();
    }


    public boolean isOnProductsPage() {
        try {
            By productsTitle = AppiumBy.xpath("//*[@text='PRODUCTS']");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productsTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isOnWebViewPage() {
        try {
            By webViewTitle = AppiumBy.xpath("//android.widget.TextView[@text='WEBVIEW SELECTION']");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(webViewTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isOnQRCodeScannerPage() {
        try {
            WebElement permissionDialogItem = driver.findElement(camaraPermissionDialog);
            return permissionDialogItem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isOnGeoLocationPage() {
        try {
            WebElement permissionDialogItem = driver.findElement(locationPermissionDialog);
            return permissionDialogItem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isOnDrawingPage() {
        try {
            By drawingHeader = AppiumBy.xpath("//android.widget.TextView[contains(@text, 'DRAWING')]");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(drawingHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isOnLoginPage() {
        return Objects.requireNonNull(driver.getPageSource()).contains("LOGIN");
    }
    public boolean isOnAboutPage() {
        return Objects.requireNonNull(driver.getPageSource()).contains("ABOUT");
    }
    public boolean isCartReset() {
        try {
            return driver.findElements(AppiumBy.xpath("//android.widget.TextView[@content-desc='test-CartBadge']")).isEmpty();
        } catch (Exception e) {
            return true; // Assume reset if badge is not found
        }
    }
    public void closeMenu() {
        By closeIcon = AppiumBy.accessibilityId("test-Close");
        driver.findElement(closeIcon).click();
    }
    public boolean isMenuVisible() {
        try {
            return driver.findElement(AppiumBy.accessibilityId("test-ALL ITEMS")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
