package tests.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CSVReaderUtils;

public class LoginValidTest extends BaseTest {

    @Test(groups = {"smoke", "regression", "full"}, dataProvider = "validLoginData", dataProviderClass = CSVReaderUtils.class)
    public void testValidLogin (String username, String password, String unusedMessage){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);
        Assert.assertTrue(loginPage.isLoginSuccessful());
    }

}
