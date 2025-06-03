package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {
    @Test
    public void testValidLogin() {
        driver.get("https://automationexercise.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();

        loginPage.login("bhintunasayami123@gmail.com", "Selenium123");

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login was not successful.");
    }
}
