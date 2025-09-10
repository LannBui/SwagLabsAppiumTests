package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductPage {
    private final AndroidDriver driver;

    private final By cartBadge = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.TextView");

    public ProductPage (AndroidDriver driver) {
        this.driver = driver;
    }

    public void addProductToCartByIndex(int index) {
        String xpath = "(//android.view.ViewGroup[@content-desc='test-ADD TO CART'])[" + (index + 1) + "]";
        try {
            WebElement addToCartBtn = driver.findElement(AppiumBy.xpath(xpath));
            addToCartBtn.click();
        } catch (StaleElementReferenceException e) {
            WebElement addToCartBtn =driver.findElement(AppiumBy.xpath(xpath));
            addToCartBtn.click();
        }
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

    public double getProductPriceByIndex(int index) {
        String xpath = "(//android.widget.TextView[@content-desc='test-Price'])[" + (index + 1) + "]";
        WebElement priceElement = driver.findElement(AppiumBy.xpath(xpath));
        String priceText = priceElement.getText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    public String getCartItemCount() {
        try {
            return driver.findElement(cartBadge).getText();
        } catch (NoSuchElementException e){
            return "0";
        }
    }

    public void openCart() {
        driver.findElement(AppiumBy.accessibilityId("test-Cart")).click();
    }

    public void openMenuAndLogout() {
        driver.findElement(AppiumBy.accessibilityId("test-Menu")).click();
        driver.findElement(AppiumBy.accessibilityId("test-LOGOUT")).click();
    }

    public void openProductDetailsByIndex (int index) {
        String xpath = "(//android.widget.TextView[@content-desc='test-Item title'])[" + (index + 1) + "]";
        try {
           WebElement item = driver.findElement(AppiumBy.xpath(xpath));
           item.click();
        } catch (StaleElementReferenceException e){
            WebElement item = driver.findElement(AppiumBy.xpath(xpath));
            item.click();
        }

    }
    public int getCartItemCountInt() {
        driver.getPageSource();
        WebElement itemCount = driver.findElement(cartBadge);
        String countText = itemCount.getText().trim();
        return Integer.parseInt(countText);
    }

    public boolean isOnProductPage() {
        return !driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='PRODUCTS']")).isEmpty();
    }
}
