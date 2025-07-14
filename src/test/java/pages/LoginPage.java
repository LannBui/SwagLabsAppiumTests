package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.PullsFiles;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private final By usernameField = AppiumBy.accessibilityId("test-Username");
    private final By passwordField = AppiumBy.accessibilityId("test-Password");
    private final By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    private final By errorMessage = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView");

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(errorMessage).getText();
        }catch (Exception e){
            return ""; // Return empty string if error message is not present
        }
    }

    public boolean isLoginSuccessful() {
        try {
            return driver.findElement(AppiumBy.xpath("//*[@text='PRODUCTS']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public boolean isOnLoginPage() {
        return !driver.findElements(AppiumBy.accessibilityId("test-LOGIN")).isEmpty();
    }
}
