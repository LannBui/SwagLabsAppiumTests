package tests.menu;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MenuPage;
import utils.ConfigLoader;

public class MenuNavigationTests extends BaseTest {
    MenuPage menuPage;
    LoginPage loginPage;

    @BeforeMethod
    public void loginAndSetup() {
      loginPage= new LoginPage(driver);
      menuPage= new MenuPage(driver);
    loginPage.login(ConfigLoader.getUsername(), ConfigLoader.getPassword());
    }

    @Test (priority = 1, groups = {"smoke", "regression", "full"})
    public void testNavigateToAllItems() {
        menuPage.openMenu();
        menuPage.tapAllItem();
        Assert.assertTrue(menuPage.isOnProductsPage(), "Should navigate to All Items page");
    }
    @Test(priority = 2, groups = {"smoke", "regression", "full"})
    public void testNavigateToWebView() {
        menuPage = new MenuPage(driver);
        menuPage.openMenu();
        menuPage.tapWebView();
        Assert.assertTrue(menuPage.isOnWebViewPage(), "Should navigate to WebView");
    }

    @Test(priority = 3, groups = {"smoke", "regression", "full"})
    public void testNavigateToQRCodeScanner() {
        menuPage = new MenuPage(driver);
        menuPage.openMenu();
        menuPage.tapQrCode();
        Assert.assertTrue(menuPage.isOnQRCodeScannerPage(), "Should navigate to QR Code Scanner");
    }

    @Test(priority = 4, groups = {"regression", "full"})
    public void testNavigateToGeoLocation() {
        menuPage = new MenuPage(driver);
        menuPage.openMenu();
        menuPage.tapGeoLoc();
        Assert.assertTrue(menuPage.isOnGeoLocationPage(), "Should navigate to Geo Location");
    }

    @Test(priority = 5, groups = {"regression", "full"})
    public void testNavigateToDrawingPage() {
        menuPage = new MenuPage(driver);
        menuPage.openMenu();
        menuPage.tapDrawing();
        Assert.assertTrue(menuPage.isOnDrawingPage(), "Should navigate to Drawing Page");
    }

    @Test(priority = 6, groups = {"regression", "full"})
    public void testNavigateToAbout() {
        menuPage = new MenuPage(driver);
        menuPage.openMenu();
        menuPage.tapAbout();
        Assert.assertTrue(menuPage.isOnAboutPage(), "Should navigate to About page (external site)");
    }

    @Test(priority = 7, groups = {"regression", "full"})
    public void testResetAppState() {
        menuPage = new MenuPage(driver);
        menuPage.openMenu();
        menuPage.tapResetApp();
        Assert.assertTrue(menuPage.isCartReset(), "Cart should be reset after tapping Reset App State");
    }

    @Test(priority = 8, groups = {"smoke", "regression", "full"}, enabled = false)
    public void testLogout() {
        menuPage = new MenuPage(driver);
        menuPage.openMenu();
        menuPage.tapLogout();
        Assert.assertTrue(menuPage.isOnLoginPage(), "Should be on Login Page after logout");
    }

    @Test(priority = 9, groups = {"regression", "full"})
    public void testCloseMenuDrawer() {
        menuPage = new MenuPage(driver);
        menuPage.openMenu();
        menuPage.closeMenu();
        Assert.assertFalse(menuPage.isMenuVisible(), "Menu should be closed");
    }
}
