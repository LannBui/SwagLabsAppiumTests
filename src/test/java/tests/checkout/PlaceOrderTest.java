package tests.checkout;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigLoader;

public class PlaceOrderTest extends BaseTest {
    private ProductPage productPage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setupAndLogin() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        loginPage.login(ConfigLoader.getUsername(), ConfigLoader.getPassword());
    }
    @Test(groups = {"regression", "full"})
    public void testPlaceOrderSuccessfully() {
        productPage.addProductToCartByIndex(0);
        productPage.openCart();
        cartPage.tapCheckout();
        checkoutPage.enterCheckoutInfo("John", "Doe", "12345");
        checkoutPage.tapContinue();
        checkoutPage.tapFinish();
        Assert.assertTrue(checkoutPage.isOrderSuccessful(),"Expected order to be successful");
    }
}
