package com.gamzeyilmazer;

import com.gamzeyilmazer.base.TestBase;
import com.gamzeyilmazer.pages.CartPage;
import com.gamzeyilmazer.pages.HomePage;
import com.gamzeyilmazer.pages.ProductPage;
import com.gamzeyilmazer.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTest extends TestBase {

    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    CartPage cartPage;
    public static final String searchText="bilgisayar";

    public HomePageTest(){
        super();
        homePage=new HomePage();
        searchResultsPage=new SearchResultsPage();
        productPage=new ProductPage();
        cartPage=new CartPage();
    }

    @Test(priority = 1)
    public void verifyHomePage(){
        Assert.assertEquals(homePage.isHomePageLogoDisplayed(),true);
    }

    @Test(priority = 2)
    public void searchProduct(){
        searchResultsPage=searchResultsPage.searchByGivenText(searchText);
    }

    @Test(priority = 3)
    public void navigateToSecondPageResults(){
        searchResultsPage=searchResultsPage.navigateToSecondPage();
        Assert.assertEquals(searchResultsPage.getPageNumber(), "2");
    }

    @Test(priority = 4)
    public void chooseProduct()throws IOException {
        productPage=searchResultsPage.chooseProduct();
        productPage.getProductDetails();

    }
    @Test(priority = 5)
    public void addProductToCart(){
        cartPage=productPage.addProductToCart();
    }
}
