package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    //Locators
    private By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By emailInput = By.xpath("//input[@data-qa='login-email']");
    private By passwordInput = By.xpath("//input[@data-qa='login-password']");
    private By loginBtn = By.xpath("//button[@data-qa='login-button']");
    private By loggedInAsText = By.xpath("//a[contains(text(),'Logged in as')]");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void goToLoginPage() {
        driver.findElement(signupLoginBtn).click();
    }

    public void login(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

    public boolean isLoginSuccessful() {
        return driver.findElement(loggedInAsText).isDisplayed();
    }
}
