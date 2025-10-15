package tests.checkout;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigLoader;

import java.util.ArrayList;
import java.util.List;

public class PriceVerificationTests extends BaseTest {
    private ProductPage productPage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private MenuPage menuPage;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;

    @BeforeMethod
    public void setupAndLogin() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        menuPage = new MenuPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        loginPage.login(ConfigLoader.getUsername(), ConfigLoader.getPassword());
    }
    @Test (priority = 1, groups = {"regression", "full"})
    public void testItemTotalTaxAndTotalCalculation() {
        productPage.addProductToCartByIndex(0);
//        productPage.addProductToCartByIndex(1);
        productPage.openCart();
        cartPage.tapCheckout();
        checkoutPage.enterCheckoutInfo("John", "Doe", "12345");
        checkoutPage.tapContinue();
        checkoutOverviewPage.scrollToSummarySection();

        double expectedItemTotal = checkoutOverviewPage.getItemPriceSum();
        double actualItemTotal = checkoutOverviewPage.getItemTotal();
        double tax = checkoutOverviewPage.getTax();
        double expectedTotal = expectedItemTotal + tax;
        double actualTotal = checkoutOverviewPage.getTotal();

        Assert.assertEquals(actualItemTotal, expectedItemTotal, 0.01, "Item total mismatch");
        Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Final total mismatch");
    }
    @Test(priority = 2, enabled = false, groups = {"regression", "full"})
    public void testTotalAfterRemovingProductBeforeCheckout() {
        productPage.addProductToCartByIndex(0);
        productPage.addProductToCartByIndex(1);
        productPage.openCart();
        cartPage.removeProductFromCartByIndex(0);
        cartPage.tapCheckout();
        checkoutPage.enterCheckoutInfo("Alice", "Smith", "45678");
        checkoutPage.tapContinue();
        checkoutOverviewPage.scrollToSummarySection();

        double expectedItemTotal = checkoutOverviewPage.getItemPriceSum();
        double actualItemTotal = checkoutOverviewPage.getItemTotal();
        double tax = checkoutOverviewPage.getTax();
        double expectedTotal = expectedItemTotal + tax;
        double actualTotal = checkoutOverviewPage.getTotal();
        Assert.assertEquals(actualItemTotal, expectedItemTotal, "Item total mismatch");
        Assert.assertEquals(expectedTotal, actualTotal, "Total mismatch");
    }
    @Test (priority = 3, groups = {"regression", "full"})
    public void testTotalWithOnlyOneProduce() {
        productPage.addProductToCartByIndex(0);
        productPage.openCart();
        cartPage.tapCheckout();
        checkoutPage.enterCheckoutInfo("Alice", "Smith", "45678");
        checkoutPage.tapContinue();
        checkoutOverviewPage.scrollToSummarySection();

        double expectedItemTotal = checkoutOverviewPage.getItemPriceSum();
        double actualItemTotal = checkoutOverviewPage.getItemTotal();
        double tax = checkoutOverviewPage.getTax();
        double expectedTotal = expectedItemTotal + tax;
        double actualTotal = checkoutOverviewPage.getTotal();
        Assert.assertEquals(actualItemTotal, expectedItemTotal, "Item total mismatch");
        Assert.assertEquals(expectedTotal, actualTotal, "Total mismatch");
    }

    @Test (priority = 4, groups = {"regression", "full"})
    public void testPriceUpdateAfterCartResetAndNewSelection() {
        productPage.addProductToCartByIndex(0);
        productPage.openCart();
        menuPage.openMenu();
        menuPage.tapResetApp();
        cartPage.tapBack();

        productPage.addProductToCartByIndex(0);
        productPage.openCart();
        cartPage.tapCheckout();
        checkoutPage.enterCheckoutInfo("Eve", "Jackson", "33445");
        checkoutPage.tapContinue();
        checkoutOverviewPage.scrollToSummarySection();

        double expectedItemTotal = checkoutOverviewPage.getItemPriceSum();
        double actualItemTotal = checkoutOverviewPage.getItemTotal();
        double tax = checkoutOverviewPage.getTax();
        double expectedTotal = expectedItemTotal + tax;
        double actualTotal = checkoutOverviewPage.getTotal();

        Assert.assertEquals(expectedTotal, actualTotal, "ItemTotal mismatch");
        Assert.assertEquals(expectedTotal, actualTotal, "Total mismatch");
    }

}
