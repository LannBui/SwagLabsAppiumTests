package tests.menu;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import pages.LoginPage;
import pages.MenuPage;
import pages.QRCodeScannerPage;

public class QRCodeScannerTests extends BaseTest {
    private LoginPage loginPage;
    private MenuPage menuPage;
    private QRCodeScannerPage qrCodeScannerPage;

    @BeforeMethod
    public void loginAndSetup() {
        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);
        qrCodeScannerPage = new QRCodeScannerPage(driver);
        loginPage.login("standard_user","secret_sauce");
        menuPage.openMenu();
    }

    @Test(priority = 1, groups = {"regression", "full"})
    public void testPermissionDialogDisplayed() {
        menuPage.tapQrCode();
        Assert.assertTrue(qrCodeScannerPage.isPermissionDialogDisplayed(), "Permission dialog not displayed");
        // Always close the dialog to avoid blocking subsequent tests
        if (qrCodeScannerPage.isPermissionDialogDisplayed()) {
            qrCodeScannerPage.denyCameraPermission();
        }
    }

    @Test (priority = 2, groups = {"regression", "full"})
    public void testDenyCameraPermissionFlow() {
        menuPage.tapQrCode();
        Assert.assertTrue(qrCodeScannerPage.isPermissionDialogDisplayed(), "Permission dialog not displayed");
        qrCodeScannerPage.denyCameraPermission();
        Assert.assertTrue(qrCodeScannerPage.isReturnedToMenu(), "App did not return to menu after denial");
    }
    @Test (priority = 3, groups = {"regression", "full"})
    public void testAllowCameraWhileUsingApp() {
        menuPage.tapQrCode();
        Assert.assertTrue(qrCodeScannerPage.isPermissionDialogDisplayed());
        qrCodeScannerPage.allowCameraWhileUsingApp();
        Assert.assertTrue(qrCodeScannerPage.isScannerDisplayed(), "Scanner not displayed after allowing permission");
    }
    @Test (priority = 4, groups = {"regression", "full"})
    public void testAllowOnlyThisTime() {
        menuPage.tapQrCode();
        Assert.assertTrue(qrCodeScannerPage.isPermissionDialogDisplayed());
        qrCodeScannerPage.allowCameraOnlyThisTime();
        Assert.assertTrue(qrCodeScannerPage.isScannerDisplayed(), "Scanner not shown for one-time permission");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
