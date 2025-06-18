package pages;

import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumUtils;

import java.util.List;

public class CartPage {
    WebDriver driver;
    private SeleniumUtils utils;

    @FindBy(xpath = "//a[contains(text(), 'Cart')]")
    public WebElement cartMenu;

    @FindBy(xpath = "//td/h4")
    public List<WebElement> productTitlesOnCart;

    @FindBy(xpath = "//td[@class='cart_price']/p")
    public List<WebElement> productPricesOnCart;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        utils = new SeleniumUtils(driver);
        PageFactory.initElements(driver, this);

    }

    public void navigateToCartsPage() {
        utils.click(cartMenu, "Cart Menu");
    }

    public JsonObject getProductDetailsForCart(int i){
        JsonObject productDetailsForProductOnCart = new JsonObject();
        productDetailsForProductOnCart.addProperty("productTitle", productTitlesOnCart.get(i).getText());
        productDetailsForProductOnCart.addProperty("productPrice", productPricesOnCart.get(i).getText());
        return productDetailsForProductOnCart;
    }
}
