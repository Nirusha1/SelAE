package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        // For singleton pattern
        if(driver.get() == null){
            WebDriverManager.chromedriver().setup();
            WebDriver newdriver = new ChromeDriver();
            newdriver.manage().window().maximize();
            driver.set(newdriver);
        }
            return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
