package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage {
    AndroidDriver driver;


    private final By checkoutButton = AppiumBy.accessibilityId("test-CHECKOUT");
    private final By continueShoppingButton = AppiumBy.accessibilityId("test-CONTINUE SHOPPING");

    public CartPage (AndroidDriver driver){
        this.driver = driver;
    }
    public int getItemCount() {
        driver.getPageSource();
        List<WebElement> cartItems = driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Item']"));
        return cartItems.size();
    }
    public void tapCheckout() {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                + "new UiSelector().text(\"CHECKOUT\"))"));
        driver.findElement(checkoutButton).click();
    }

    public void tapContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    public boolean isOnCartPage() {
        return !driver.findElements(checkoutButton).isEmpty();
    }

    public boolean isCartEmpty() {
        return !driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Your cart is empty']")).isEmpty()
        || driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Item']")).isEmpty();
    }

    public void removeProductFromCartByIndex(int index) {
        String xpath = "(//android.view.ViewGroup[@content-desc='test-REMOVE'])[" + (index + 1) + "]";
        try {
            WebElement removeFromCartBtn = driver.findElement(AppiumBy.xpath(xpath));
            removeFromCartBtn.click();
        } catch (StaleElementReferenceException e) {
            WebElement removeFromCartBtn = driver.findElement(AppiumBy.xpath(xpath));
            removeFromCartBtn.click();
        }
    }
    public void tapBack() {
        driver.navigate().back();
    }
}
