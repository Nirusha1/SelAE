package utils;

import listeners.ExtentTestNGListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtils {
    private WebDriver driver;
    private WebDriverWait wait;

    public SeleniumUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(WebElement element, String elementName) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        ExtentTestNGListener.info("Clicked: " + elementName);
    }

    public void sendKeys(WebElement element, String text, String elementName) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(text);
        ExtentTestNGListener.info("Entered text in: " + elementName + " | Value: " + text);
    }

    public String getText(WebElement element, String elementName) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        ExtentTestNGListener.info("Fetched text from: " + elementName + " | Text: " + text);
        return text;
    }

    public void waitForVisibility(WebElement element, String elementName) {
        wait.until(ExpectedConditions.visibilityOf(element));
        ExtentTestNGListener.info("Element visible: " + elementName);
    }

    public void waitForPageToLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        ExtentTestNGListener.info("Page loaded completely");
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        ExtentTestNGListener.info("Scrolled view to: " + element.getTagName());
    }

    public void hover(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        ExtentTestNGListener.info("Hovered: " + element.getTagName());
    }

}
