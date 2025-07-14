package tests.cart;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductPage;

import java.time.Duration;

public class CartCountTests extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    ProductDetailsPage productDetailsPage;

    @BeforeMethod
    public void loginAndSetup() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        loginPage.login("standard_user","secret_sauce");
    }

    @Test(priority = 1, groups = {"smoke", "regression", "full"})
    public void testAddSingleProduct() {
        productPage.addProductToCartByIndex(0);
        Assert.assertEquals(productPage.getCartItemCount(),"1", "Cart icon should show 1 item");
    }

    @Test(priority = 2, groups = {"regression", "full"}, enabled = false)
    public void testAddTwoProducts() {
        productPage.addProductToCartByIndex(0);
        productPage.addProductToCartByIndex(1);
        Assert.assertEquals(productPage.getCartItemCount(), "2", "Cart icon should show 2 items");
    }

    @Test(priority = 3, groups = {"regression", "full"}, enabled = false)
    public void testRemoveOneProduct() {
        productPage.addProductToCartByIndex(0);
        productPage.addProductToCartByIndex(1);
        productPage.removeProductFromCartByIndex(0);
        Assert.assertEquals(productPage.getCartItemCount(), "1", "Cart icon should show 1 item");
    }

    @Test(priority = 4, groups = {"regression", "full"}, enabled = false)
    public void testRemoveAllProducts() {
        productPage.addProductToCartByIndex(0);
        productPage.addProductToCartByIndex(1);
        productPage.removeProductFromCartByIndex(0);
        productPage.removeProductFromCartByIndex(1);
        Assert.assertEquals(productPage.getCartItemCount(), "0", "Cart icon should be 0");
    }
    @Test(priority = 5, groups = {"regression", "full"})
    public void testCartResetAfterLogoutLogin() {
        productPage.addProductToCartByIndex(0);
        Assert.assertEquals(productPage.getCartItemCount(),"1", "Cart should have 1 item before logout");
        productPage.openMenuAndLogout();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed after logout");
        Assert.assertEquals(productPage.getCartItemCount(),"0", "Cart item should be 0 after logout");
    }

    @Test(priority = 6, groups = {"regression", "full"}, enabled = false)
    public void testCartCountMatchesCartPage() {
        productPage.addProductToCartByIndex(0);
        productPage.addProductToCartByIndex(1);
//        Assert.assertEquals(productPage.getCartItemCount(), "2", "Cart icon should show 2 items");
        productPage.openCart();
        int itemCount = cartPage.getItemCount();
        Assert.assertEquals(itemCount, 2, "Cart page should list 2 items");
    }

    @Test(priority = 7, groups = {"regression", "full"})
    public void testCartCountNotPreservedAfterAppRelaunch() {
        productPage.addProductToCartByIndex(0);
        String countBefore = productPage.getCartItemCount();

        driver.terminateApp("com.swaglabsmobileapp");
        driver.activateApp("com.swaglabsmobileapp");

        String countAfter = productPage.getCartItemCount();
        Assert.assertEquals(countAfter,"0", "Cart count should not be preserved after relaunch");
    }

    @Test(priority = 8, groups = {"regression", "full"})
    public void testAddFromProductDetailPage() {
        productPage.openProductDetailsByIndex(0);
        productDetailsPage.clickAddToCart();
        driver.navigate().back();
        Assert.assertEquals(productPage.getCartItemCount(),"1", "Cart count should reflect item added from details page");
    }

    @Test(priority = 9, groups = {"regression", "full"}, enabled = false)
    public void testCartCountPersistsAfterBackground() {
        productPage.addProductToCartByIndex(0);
        productPage.addProductToCartByIndex(1);

        int before = productPage.getCartItemCountInt();
        driver.runAppInBackground(Duration.ofSeconds(5));

        int after = productPage.getCartItemCountInt();
        Assert.assertEquals(after, before, "Cart count should persist after backgrounding the app");
    }

    @Test (priority = 10, groups = {"regression", "full"}, enabled = false)
    public void testCartCountConsistencyAcrossScreens() {
        productPage.addProductToCartByIndex(0);
        productPage.openProductDetailsByIndex(1);

        productDetailsPage.navigateBackToProductList();

        int count = productPage.getCartItemCountInt();
        Assert.assertEquals(count, 1, "Cart count should remain consistent across screens");

    }
}
