package tests;

import listeners.ExtentTestNGListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

@Listeners(ExtentTestNGListener.class)
public class LoginTest extends BaseTest {
    @Test

    public void testValidLogin() {
        ExtentTestNGListener.test.get().info("Navigating to login page");
        String currentURL = "https://automationexercise.com/";
        driver.get(currentURL);
        ExtentTestNGListener.test.get().info("Navigated to: " + currentURL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();

        loginPage.login("bhintunasayami123@gmail.com", "Selenium123");
        ExtentTestNGListener.test.get().info("Entered email"+"bhintunasayami123@gmail.com");

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login was not successful.");
        ExtentTestNGListener.test.get().pass("Login test completed.");
    }
}
