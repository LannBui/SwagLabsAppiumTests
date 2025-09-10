package base;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigLoader;

import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        ConfigLoader.loadConfig();
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName(ConfigLoader.getDeviceName())
                .setAutomationName("UiAutomator2")
                .setApp(ConfigLoader.getAppPath())
                .setAppWaitActivity("*");
        driver = new AndroidDriver(new URL(ConfigLoader.getBaseUrl()), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigLoader.getWaitTimeout()));

        System.out.println("Test started with baseUrl: " + ConfigLoader.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
