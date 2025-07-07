package tests;

import listeners.ExtentTestNGListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

@Listeners(ExtentTestNGListener.class)
public class LoginTest extends BaseTest {
    @Test(priority = 1, description = "Verify valid login")
    public void testValidLogin() {
        ExtentTestNGListener.test.get().info("Navigating to login page");
        String currentURL = "https://automationexercise.com/";
        driver.get(currentURL);
        ExtentTestNGListener.test.get().info("Navigated to: " + currentURL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();

        loginPage.login(ConfigReader.get("test.user.email"), ConfigReader.get("test.user.password"));
        ExtentTestNGListener.test.get().info("Entered email"+ ConfigReader.get("test.user.email"));

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login was not successful.");
        ExtentTestNGListener.test.get().pass("Login test completed.");
    }

    @Test(priority = 2, description = "Verify invalid login")
    public void testInvalidLogin() {
        ExtentTestNGListener.test.get().info("Navigating to login page");
        String currentURL = "https://automationexercise.com/";
        driver.get(currentURL);
        ExtentTestNGListener.test.get().info("Navigated to: " + currentURL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();

        loginPage.login("bhintunasayami123@gmail.com", "Selenium1233");
        Assert.assertTrue(loginPage.isLoginInvalidMessageDisplayed(), "Login was not successful.");
        ExtentTestNGListener.test.get().pass("Invalid Login test completed.");
    }
}
