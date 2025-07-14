package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    AndroidDriver driver;


    private final By firstNameField = AppiumBy.accessibilityId("test-First Name");
    private final By lastNameField = AppiumBy.accessibilityId("test-Last Name");
    private final By zipField = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private final By continueButton = AppiumBy.accessibilityId("test-CONTINUE");
    private final By checkoutButton = AppiumBy.accessibilityId("test-CHECKOUT");
    private final By continueShoppingButton = AppiumBy.accessibilityId("test-CONTINUE SHOPPING");
    private final By backButton = AppiumBy.accessibilityId("test-BACK");
    private final By successMessage = AppiumBy.xpath("//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']");

    public CheckoutPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void enterCheckoutInfo(String firstName,String lastName, String zip) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(zipField).sendKeys(zip);
    }

    public void tapCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void tapContinue() {
        driver.findElement(continueButton).click();
    }

    public void tapFinish() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                        + "new UiSelector().text(\"FINISH\"))"));
        driver.findElement(AppiumBy.accessibilityId("test-FINISH")).click();
    }

    public void tapCancel() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                        + "new UiSelector().text(\"CANCEL\"))"));
        driver.findElement(AppiumBy.accessibilityId("test-CANCEL")).click();

    }

    public boolean isOrderSuccessful() {
        return !driver.findElements(successMessage).isEmpty();
    }
    public String getErrorMessage() {
        try {
            By errorLocator = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView");
            return driver.findElement(errorLocator).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public boolean isOnCheckoutInfoPage() {
        return !driver.findElements(AppiumBy.accessibilityId("test-CONTINUE")).isEmpty();
    }
    public void tapBack() {
        driver.navigate().back();
    }
}
