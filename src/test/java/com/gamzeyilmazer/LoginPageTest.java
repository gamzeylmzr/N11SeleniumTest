package com.gamzeyilmazer;

import com.gamzeyilmazer.base.TestBase;
import com.gamzeyilmazer.pages.HomePage;
import com.gamzeyilmazer.pages.LoginPage;
import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginPageTest extends TestBase{

    LoginPage loginPage;
    HomePage homePage;

    public LoginPageTest(){
        super();
        initialize();
        homePage=new HomePage();
    }

    @Test(priority = 1, dataProvider = "getCredentials")
    public void login(String [] loginData){
        loginPage=homePage.navigateToLoginPage();
        loginPage.login(loginData[0],loginData[1]);
        Assert.assertEquals(homePage.isUsernameDisplayed(),prop.getProperty("username"));
    }

    @DataProvider(name="getCredentials")
    public static Object[][] getDataFromCSV() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(System.getProperty("user.dir")+"/src/test/resources/credentials.csv"),',');
        List<String[]> csvData=csvReader.readAll();
        Object[][] csvDataObject=new Object[csvData.size()][2];
        for (int i=0;i<csvData.size();i++) {
            csvDataObject[i]=csvData.get(i);
        }
        return  csvDataObject;
    }


}
