package tests.checkout;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.CSVReaderUtils;
import utils.ConfigLoader;

public class CheckoutValidationTests extends BaseTest {
    @Test(groups = {"regression", "full"}, dataProvider = "checkoutData", dataProviderClass = CSVReaderUtils.class)
    public void testCheckoutValidation(String firstName, String lastName, String zip, String expectedResult, String expectedMessage){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigLoader.getUsername(), ConfigLoader.getPassword());

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCartByIndex(0);
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.tapCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutInfo(firstName, lastName, zip);
        checkoutPage.tapContinue();

        if (expectedResult.equalsIgnoreCase("pass")){
            checkoutPage.tapFinish();
            Assert.assertTrue(checkoutPage.isOrderSuccessful(), "Expected order to be successful");
        }else{
            String actualError = checkoutPage.getErrorMessage();
            Assert.assertEquals(actualError, expectedMessage, "Validation message mismatch");
        }
    }
}
