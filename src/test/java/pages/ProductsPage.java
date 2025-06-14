package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumUtils;

import java.util.List;

public class ProductsPage {
    WebDriver driver;
    private SeleniumUtils utils;

    @FindBy(xpath = "//a[contains(text(), 'Products')]")
    public WebElement productsMenu;

    @FindBy(xpath="//h2[contains(text(), 'All Products')]")
    public WebElement allProductsPageTitle;

    @FindBy(xpath="//div[@class='single-products']")
    public List<WebElement> productsList;

    @FindBy(xpath = "//a[contains(text(), 'View Product')]")
    public List<WebElement> viewProductButtons;

    @FindBy(xpath="//div[@class='productinfo text-center']/p")
    public List<WebElement> productsPageProductTitles;

    @FindBy(xpath = "//div[@class='product-information']/h2")
    public WebElement productsDetailsTitle;

    @FindBy(xpath="//div[@class='product-information']/p")
    public List<WebElement> productsDetailsProductInformations;

    @FindBy(xpath="//div[@class='product-information']/span")
    public WebElement productsDetailsProductsPrice;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        utils = new SeleniumUtils(driver);
        PageFactory.initElements(driver, this);
        
    }

    public void navigateToProductsPage() {
        utils.click(productsMenu, "Products Menu");
    }

    public void navigateToFirstProductDetailPage(){
        utils.scrollToElement(viewProductButtons.get(0));
        utils.click(viewProductButtons.getFirst(), "View Product Button");
    }

    public String getFirstProductTitle() {
        return utils.getText(productsPageProductTitles.getFirst(), "First Product Title");
    }

}
