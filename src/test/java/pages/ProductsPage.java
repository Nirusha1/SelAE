package pages;

import com.google.gson.JsonObject;
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

    @FindBy(xpath = "//input[@id='search_product']")
    public WebElement searchProductField;

    @FindBy(xpath="//button[@id='submit_search']")
    public WebElement searchButton;

    @FindBy(xpath = "(//a[@data-product-id='1'])[1]")
    public WebElement firstProductAddToCartButton;

    @FindBy(xpath = "(//a[@data-product-id='2'])[1]")
    public WebElement secondProductAddToCartButton;

    @FindBy(xpath = "(//div[@class='modal-body']//p)[1]")
    public WebElement productAddedToCartSuccessMessage;

    @FindBy(xpath="//div[@class='modal-footer']/button")
    public WebElement continueShoppingButton;

    @FindBy(xpath="//div[@class='productinfo text-center']/p")
    public List<WebElement> productInfoTitles;

    @FindBy(xpath = "//div[@class='productinfo text-center']/h2")
    public List<WebElement> productInfoPrices;






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

    public void searchProduct(String searchKeyword){
        utils.sendKeys(searchProductField, searchKeyword, "Enter Search String on Search Product Field");
        utils.click(searchButton, "Search Product");
    }

    public void hoverOverProductCard(int i){
        utils.hover(productsList.get(i));
    }

    public void addFirstProductToCart(){
        utils.click(firstProductAddToCartButton, "Add Product To Cart");
    }

    public void addSecondProductToCart(){
        utils.click(secondProductAddToCartButton, "Add Product To Cart");
    }

    public String getProductAddedToCartSuccessMessage(){
        return utils.getText(productAddedToCartSuccessMessage, "Retreive text from success message for product added to cart");
    }

    public void clickContinueShoppingButton(){
        utils.click(continueShoppingButton, "Continue Shopping");
    }

    public JsonObject getProductDetail(int i){
        JsonObject productDetail = new JsonObject();

        String productTitle = utils.getText(productInfoTitles.get(i), "Product Title");
        String productPrice = utils.getText(productInfoPrices.get(i), "Product Price");

        productDetail.addProperty("productTitle", productTitle);
        productDetail.addProperty("productPrice", productPrice);

        return productDetail;


    }

    public void scrollToProductList(){
        utils.scrollToElement(firstProductAddToCartButton);
    }



}
