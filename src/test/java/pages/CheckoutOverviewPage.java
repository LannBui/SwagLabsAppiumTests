package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage {
    AndroidDriver driver;

    private final By itemTotalLocator = By.xpath("//android.widget.TextView[contains(@text, 'Item total')]");
    private final By taxLocator = By.xpath("//android.widget.TextView[contains(@text, 'Tax')]");
    private final By totalLocator = By.xpath("//android.widget.TextView[contains(@text, 'Total')]");
    private final By productPriceList = By.xpath("//android.widget.TextView[contains(@text, '$') and not(contains(@text, 'Item total')) and not(contains(@text, 'Tax')) and not(contains(@text, 'Total'))]");

    public CheckoutOverviewPage(AndroidDriver driver){
        this.driver = driver;
    }
    private double extractAmount (String text){
        String number = text.replaceAll("[^0-9.]","");
        return Double.parseDouble(number);
    }
    public double getItemTotal() {
        WebElement itemTotal = driver.findElement(itemTotalLocator);
        return extractAmount(itemTotal.getText());
    }
    public double getTax() {
        WebElement tax = driver.findElement(taxLocator);
        return extractAmount(tax.getText());
    }
    public double getTotal() {
        WebElement total = driver.findElement(totalLocator);
        return extractAmount(total.getText());
    }

    public double getItemPriceSum() {
        List<WebElement> priceElements = driver.findElements(productPriceList);
        return priceElements.stream()
                .mapToDouble(e -> extractAmount(e.getText()))
                .sum();
    }
    public void scrollToSummarySection() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().textContains(\"Item total\"))"));
    }


}
