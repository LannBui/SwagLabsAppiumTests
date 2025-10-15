package sanity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.*;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class IOSSafariSmokeTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        XCUITestOptions opts = new XCUITestOptions()
                .setPlatformName("iOS")
                .setAutomationName("XCUITest")
                .setDeviceName("iPhone 14")     // no "(16.4)" in the name
                .setPlatformVersion("16.4")
                .withBrowserName("Safari")
                .setNewCommandTimeout(java.time.Duration.ofSeconds(180));
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), opts);
    }

    @Test
    public void openExample() {
        driver.get("https://example.com");
        assertTrue(driver.getTitle().toLowerCase().contains("example"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}