package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ProductDetailsPage {
    private AndroidDriver driver;

    public ProductDetailsPage (AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickAddToCart() {
        String scrollable = "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().description(\"test-ADD TO CART\"))";
        driver.findElement(AppiumBy.androidUIAutomator(scrollable));
        driver.findElement(AppiumBy.accessibilityId("test-ADD TO CART")).click();
    }

    public void navigateBackToProductList() {
        driver.navigate().back();
    }
}
