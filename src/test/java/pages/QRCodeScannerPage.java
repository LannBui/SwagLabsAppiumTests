package pages;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class QRCodeScannerPage {
    private AndroidDriver driver;
    // Core scanner UI elements
    private final By scannerHeader = AppiumBy.xpath("//*[contains(@text, 'Scan')]");
    private final By cancelButton = AppiumBy.accessibilityId("test-Cancel");

    // Permission dialog UI
    private final By permissionDialog = AppiumBy.id("com.android.permissioncontroller:id/grant_dialog");
    private final By allowWhileUsingAppBtn = AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private final By allowOnlyThisTimeBtn = AppiumBy.id("com.android.permissioncontroller:id/permission_allow_one_time_button");
    private final By denyBtn = AppiumBy.id("com.android.permissioncontroller:id/permission_deny_button");

    // Fallback / denial behavior
    private final By deniedToastMessage = AppiumBy.xpath("//*[contains(@text, 'Permission denied')]");
    private final By menuHeader = AppiumBy.xpath("//*[contains(@text, 'ALL ITEMS')]"); // App returns to menu
    private final By returnedToScannerMessage = AppiumBy.xpath("//*[contains(@text, 'Scan a QR Code that contains a url')]");

    // Use this locator to verify the user is on the QR scanner screen after granting permission
    private final By scannerReadyMessage = AppiumBy.xpath("//*[contains(@text, 'Scan a QR Code that contains a url')]");

    public QRCodeScannerPage (AndroidDriver driver){
        this.driver = driver;
    }
    public boolean isScannerDisplayed() {
        try {
            return driver.findElement(scannerHeader).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isReturnedToMenu() {
        try {
            return driver.findElement(returnedToScannerMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isPermissionDialogDisplayed() {
        try {
            return driver.findElement(permissionDialog).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }
    public void tapCancel() {
        driver.findElement(cancelButton).click();
    }
    public void denyCameraPermission() {
       driver.findElement(denyBtn).click();
    }
    public boolean isPermissionDeniedMessageShown() {
        try {
            return driver.findElement(deniedToastMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void allowCameraWhileUsingApp() {
        driver.findElement(allowWhileUsingAppBtn).click();
    }

    public void allowCameraOnlyThisTime() {
        driver.findElement(allowOnlyThisTimeBtn).click();
    }

    public boolean isScannerReady() {
        try {
            return driver.findElement(scannerReadyMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
