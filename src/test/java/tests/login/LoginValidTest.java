package tests.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CSVReaderUtils;
import utils.ConfigLoader;

public class LoginValidTest extends BaseTest {

    @Test(groups = {"smoke", "regression", "full"})
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigLoader.getUsername(), ConfigLoader.getPassword());
        Assert.assertTrue(loginPage.isLoginSuccessful());
    }

}
