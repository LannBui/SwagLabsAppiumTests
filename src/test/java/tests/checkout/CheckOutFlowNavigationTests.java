package tests.checkout;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CheckOutFlowNavigationTests extends BaseTest {
    private ProductPage productPage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private MenuPage menuPage;

    @BeforeMethod
    public void setupAndLogin() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        menuPage = new MenuPage(driver);
        loginPage.login("standard_user","secret_sauce");
    }
    @Test (priority = 1, groups = {"regression", "full"})
    public void testProceedToCheckoutSuccessfully() {
        productPage.addProductToCartByIndex(0);
        productPage.openCart();
        cartPage.tapCheckout();
        checkoutPage.enterCheckoutInfo("John", "Doe", "12345");
        checkoutPage.tapContinue();
        checkoutPage.tapFinish();
        Assert.assertTrue(checkoutPage.isOrderSuccessful(),"Expected order to be successful");
    }
    @Test (priority = 2, groups = {"regression", "full"})
    public void testCancelCheckoutFromInformationPage() {
        productPage.openCart();
        cartPage.tapCheckout();
        checkoutPage.tapCancel();
        Assert.assertTrue(productPage.isOnProductPage(), "Should navigate back to Cart");
    }
    @Test (priority = 3, groups = {"regression", "full"})
    public void testContinueShoppingFromCart() {
        productPage.openCart();
        cartPage.tapContinueShopping();
        Assert.assertTrue(productPage.isOnProductPage(), "Should navigate back to product list");
    }
    @Test (priority = 4, groups = {"regression", "full"})
    public void testBackFromOverviewToInfo() {
        productPage.addProductToCartByIndex(0);
        productPage.openCart();
        cartPage.tapCheckout();
        checkoutPage.enterCheckoutInfo("John", "Doe", "12345");
        checkoutPage.tapContinue();
        checkoutPage.tapBack();
        Assert.assertTrue(checkoutPage.isOnCheckoutInfoPage(), "Should return to info screen");
    }
    @Test (priority = 5, groups = {"regression", "full"})
    public void testCancelFromOverview() {
        productPage.addProductToCartByIndex(0);
        productPage.openCart();
        cartPage.tapCheckout();
        checkoutPage.enterCheckoutInfo("John", "Doe", "12345");
        checkoutPage.tapCancel();
        Assert.assertTrue(productPage.isOnProductPage(), "Should navigate back to product page");
    }
    @Test (priority = 6, groups = {"regression", "full"})
    public void testResetAppFromMenuDuringCheckout() {
        productPage.addProductToCartByIndex(0);
        productPage.openCart();
        cartPage.tapCheckout();
        menuPage.openMenu();
        menuPage.tapResetApp();
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty after reset");
    }
    @Test (priority = 7, groups = {"regression", "full"})
    public void testNavigateHomeFromMenuOnCheckout() {
        productPage.openCart();
        cartPage.tapCheckout();
        menuPage.openMenu();
        menuPage.tapAllItem();
        Assert.assertTrue(productPage.isOnProductPage(), "Should navigate back to product page");
    }
    @Test (priority = 8, groups = {"regression", "full"})
    public void testLogoutFromMenuDuringCheckout() {
        productPage.openCart();
        cartPage.tapCheckout();
        menuPage.openMenu();
        menuPage.tapLogout();
        Assert.assertTrue(loginPage.isOnLoginPage(), "Should navigate to login page");
    }
}
