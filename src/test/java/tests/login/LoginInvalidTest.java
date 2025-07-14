package tests.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CSVReaderUtils;

public class LoginInvalidTest extends BaseTest {

    @Test(groups = {"regression", "full"}, dataProvider = "invalidLoginData", dataProviderClass = CSVReaderUtils.class)
    public void testInvalidLogin(String username, String password, String expectedMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Error message mismatch");

    }
}
