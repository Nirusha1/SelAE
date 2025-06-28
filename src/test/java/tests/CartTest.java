package tests;

import com.google.gson.JsonObject;
import listeners.ExtentTestNGListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;
import utils.BaseTest;

@Listeners(ExtentTestNGListener.class)
public class CartTest extends BaseTest {

    @Test(priority = 1, description = "Verify Cart Navigation and Cart product details")
    public void testAddProductsToCart() {
        ExtentTestNGListener.test.get().info("Navigating to Products page");
        String currentURL = "https://automationexercise.com/";
        driver.get(currentURL);
        ExtentTestNGListener.test.get().info("Navigated to: " + currentURL);

        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        productsPage.navigateToProductsPage();
        JsonObject firstProductDetail = productsPage.getProductDetail(0);
        JsonObject secondProductDetail = productsPage.getProductDetail(1);
        ExtentTestNGListener.test.get().info("Product Details for First Product on Products Page: " + firstProductDetail.toString());
        ExtentTestNGListener.test.get().info("Product Details for Second Product on Products Page: " + secondProductDetail.toString());

        productsPage.scrollToProductList();
        productsPage.addFirstProductToCart();
        Assert.assertTrue(productsPage.getProductAddedToCartSuccessMessage().contentEquals("Your product has been added to cart."));

        productsPage.clickContinueShoppingButton();
        productsPage.addSecondProductToCart();
        Assert.assertTrue(productsPage.getProductAddedToCartSuccessMessage().contentEquals("Your product has been added to cart."));
        productsPage.clickContinueShoppingButton();
        cartPage.navigateToCartsPage();
        JsonObject firstProductDetailOnCart = cartPage.getProductDetailsForCart(0);
        JsonObject secondProductDetailOnCart = cartPage.getProductDetailsForCart(1);
        ExtentTestNGListener.test.get().info("Product Details for First Product on Cart: " + firstProductDetailOnCart.toString());
        ExtentTestNGListener.test.get().info("Product Details for Second Product on Cart: " + secondProductDetailOnCart.toString());
        Assert.assertEquals(firstProductDetailOnCart, firstProductDetail);
        ExtentTestNGListener.test.get().info("Product details on products page and cart matched for first product");
        Assert.assertEquals(secondProductDetailOnCart, secondProductDetail);
        ExtentTestNGListener.test.get().info("Product details on products page and cart matched for second product");
    }

    @Test(dependsOnMethods = "testAddProductsToCart", description = "Verify product Removal from Cart")
    public void testRemoveProductsFromCart() {
        CartPage cartPage = new CartPage(driver);
        cartPage.deleteProductFromCart();
        Assert.assertTrue(cartPage.emptyCartSpan.isDisplayed());
        ExtentTestNGListener.test.get().info("Product Deletion From Cart Successfully Verified");
    }
}
