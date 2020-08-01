package com.gamzeyilmazer;

import com.gamzeyilmazer.base.TestBase;
import com.gamzeyilmazer.pages.*;
import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class N11Test extends TestBase{

    LoginPage loginPage;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    CartPage cartPage;
    public static final String searchText="bilgisayar";

    public N11Test(){
        super(); //to call TestBase constructor
    }

    @BeforeClass
    public void setUp(){
        initialize();
        homePage=new HomePage();
        searchResultsPage=new SearchResultsPage();
        productPage=new ProductPage();
        cartPage=new CartPage();
    }

//    @AfterClass
//    public void tearDown(){
//        driver.quit();
//    }

    @Test(priority = 1, dataProvider = "getCredentials")
    public void loginTest(String [] loginData){
        Assert.assertEquals(homePage.isHomePageLogoDisplayed(),true);
        loginPage=homePage.navigateToLoginPage();
        loginPage.login(loginData[0],loginData[1]);
        Assert.assertEquals(homePage.isUsernameDisplayed(),prop.getProperty("username"));
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
    public void chooseProduct()throws IOException{
        productPage=searchResultsPage.chooseProduct();
        productPage.getProductDetails();

    }
    @Test(priority = 5)
    public void addProductToCart(){
        cartPage=productPage.addProductToCart();
    }

    @Test(priority = 6)
    public void verifyPricesProductAndCartPages(){
        Assert.assertEquals(cartPage.getPriceFromCartPage(),cartPage.getPriceFromText());
    }

    @Test(priority = 7)
    public void increaseAndVerifyQuantity(){
        cartPage.increaseQuantity();
        Assert.assertEquals(cartPage.getQuantity(),"2");
    }

     @Test(priority =8)
     public void deleteProductFromCart(){
        cartPage.deleteProduct();
        Assert.assertEquals(cartPage.isProductDeleted(),true);
     }


    @DataProvider(name="getCredentials")
    public static Object[][] getDataFromCSV() throws IOException{
        CSVReader csvReader = new CSVReader(new FileReader(System.getProperty("user.dir")+"/src/test/resources/credentials.csv"),',');
        List<String[]> csvData=csvReader.readAll();
        Object[][] csvDataObject=new Object[csvData.size()][2];
        for (int i=0;i<csvData.size();i++) {
            csvDataObject[i]=csvData.get(i);
        }
        return  csvDataObject;
    }


}
