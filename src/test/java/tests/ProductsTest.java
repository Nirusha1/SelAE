package tests;

import listeners.ExtentTestNGListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

@Listeners(ExtentTestNGListener.class)
public class ProductsTest extends BaseTest {
    @Test
    public void testProductsPage() {
        ExtentTestNGListener.test.get().info("Navigating to Products page");
        String currentURL = "https://automationexercise.com/";
        driver.get(currentURL);
        ExtentTestNGListener.test.get().info("Navigated to: " + currentURL);

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.navigateToProductsPage();
        Assert.assertTrue(productsPage.allProductsPageTitle.isDisplayed());
        ExtentTestNGListener.test.get().pass("Products Page was navigated successfully");

        Assert.assertFalse(productsPage.productsList.isEmpty());
        ExtentTestNGListener.test.get().pass("Product List is displayed successfully");

        String expectedProductTitle = productsPage.getFirstProductTitle();
        productsPage.navigateToFirstProductDetailPage();
        Assert.assertTrue(driver.getCurrentUrl().contains("details"));
        ExtentTestNGListener.test.get().pass("Product detail page is navigated successfully and the current url is"+driver.getCurrentUrl());

        Assert.assertTrue(productsPage.productsDetailsTitle.getText().contains(expectedProductTitle));
        ExtentTestNGListener.test.get().pass("Product detail page title matched.");
        Assert.assertTrue(productsPage.productsDetailsProductInformations.getFirst().getText().contains("Category"));
        ExtentTestNGListener.test.get().pass("Product category is displayed.");
        Assert.assertTrue(productsPage.productsDetailsProductInformations.get(1).getText().contains("Availability"));
        ExtentTestNGListener.test.get().pass("Product availability is displayed.");
        Assert.assertTrue(productsPage.productsDetailsProductInformations.get(2).getText().contains("Condition"));
        ExtentTestNGListener.test.get().pass("Product condition is displayed.");
        Assert.assertTrue(productsPage.productsDetailsProductInformations.get(3).getText().contains("Brand"));
        ExtentTestNGListener.test.get().pass("Product brand is displayed.");
        Assert.assertTrue(productsPage.productsDetailsProductsPrice.getText().contains("Rs."));
        ExtentTestNGListener.test.get().pass("Product price is displayed.");

    }
}
