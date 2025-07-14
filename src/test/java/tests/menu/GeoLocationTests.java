package tests.menu;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import pages.GeoLocationPage;
import pages.LoginPage;
import pages.MenuPage;

public class GeoLocationTests extends BaseTest {
    private LoginPage loginPage;
    private MenuPage menuPage;
    private GeoLocationPage geoLocationPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);
        geoLocationPage = new GeoLocationPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(priority = 1, groups = {"regression", "full"})
    public void testNavigateToGeoLocation() {
        menuPage.openMenu();
        menuPage.tapGeoLoc();
        Assert.assertTrue(geoLocationPage.isPermissionDialogDisplayed(), "Permission dialog should appear");
    }

    @Test(priority = 2, groups = {"regression", "full"})
    public void testGeoLocationPermissionDialogAppears() {
        menuPage.openMenu();
        menuPage.tapGeoLoc();
        Assert.assertTrue(geoLocationPage.isPermissionDialogDisplayed(), "Permission dialog should appear");
    }

    @Test(priority = 3, groups = {"regression", "full"})
    public void testAllowLocationPermission() {
        menuPage.openMenu();
        menuPage.tapGeoLoc();
        Assert.assertTrue(geoLocationPage.isPermissionDialogDisplayed(), "Permission dialog should appear");
        geoLocationPage.allowLocationWhileUsingApp();
        Assert.assertTrue(geoLocationPage.isLocationAccuracyDialogDisplayed(), "Location Accuracy dialog should appear");
    }

    @Test(priority = 4, groups = {"regression", "full"})
    public void testDenyLocationPermission() {
        menuPage.openMenu();
        menuPage.tapGeoLoc();
        Assert.assertTrue(geoLocationPage.isPermissionDialogDisplayed(), "Permission dialog should appear");
        geoLocationPage.denyLocationPermission();
        Assert.assertTrue(geoLocationPage.isGeoLocationInfoDisplayed(), "Geo Location info should be displayed after turning on Location Accuracy");
    }

    @Test(priority = 5, groups = {"regression", "full"})
    public void testAllowOnlyThisTimeLocationPermission() {
        menuPage.openMenu();
        menuPage.tapGeoLoc();
        Assert.assertTrue(geoLocationPage.isPermissionDialogDisplayed(), "Permission dialog should appear");
        geoLocationPage.allowLocationOnlyThisTime();
        Assert.assertTrue(geoLocationPage.isGeoLocationInfoDisplayed(), "Geo Location info should be displayed after turning on Location Accuracy");
    }

    @Test(priority = 6, groups = {"regression", "full"})
    public void testLocationAccuracyDialogNoThanks() {
        menuPage.openMenu();
        menuPage.tapGeoLoc();
        Assert.assertTrue(geoLocationPage.isPermissionDialogDisplayed(), "Permission dialog should appear");
        geoLocationPage.allowLocationWhileUsingApp();
        Assert.assertTrue(geoLocationPage.isLocationAccuracyDialogDisplayed(), "Location Accuracy dialog should appear");
        geoLocationPage.tapNoThanksOnLocationAccuracy();
    }

    @Test(priority = 7, groups = {"regression", "full"})
    public void testLocationAccuracyDialogTurnOn() {
        menuPage.openMenu();
        menuPage.tapGeoLoc();
        Assert.assertTrue(geoLocationPage.isPermissionDialogDisplayed(), "Permission dialog should appear");
        geoLocationPage.allowLocationWhileUsingApp();
        Assert.assertTrue(geoLocationPage.isLocationAccuracyDialogDisplayed(), "Location Accuracy dialog should appear");
        geoLocationPage.tapTurnOnLocationAccuracy();
        Assert.assertTrue(geoLocationPage.isGeoLocationInfoDisplayed(), "Geo Location info should be displayed after turning on Location Accuracy");
    }

    @AfterMethod
    public void clearAppAndGmsData() throws Exception {
        String appPackage = "com.swaglabsmobileapp";
        String gmsPackage = "com.google.android.gms";
        Runtime.getRuntime().exec("adb shell pm clear " + appPackage).waitFor();
        Runtime.getRuntime().exec("adb shell pm clear " + gmsPackage).waitFor();
    }
}