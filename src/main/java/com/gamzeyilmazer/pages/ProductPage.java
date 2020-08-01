package com.gamzeyilmazer.pages;

import com.gamzeyilmazer.base.TestBase;
import com.gamzeyilmazer.helpers.FileHelpers;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ProductPage extends TestBase{
    FileHelpers fh=new FileHelpers();
    private String filePath=System.getProperty("user.dir")+"/src/test/resources/proDetails.txt";

    @FindBy(className = "proName")
    WebElement productName;

    @FindBy(className = "newPrice")
    WebElement productPrice;

    @FindBy(xpath = "//a[@title='Sepete Ekle']")
    WebElement addCartButton;

    @FindBy(className = "myBasket")
    WebElement basketIcon;

    public ProductPage(){
        PageFactory.initElements(driver,this);
    }

    public void getProductDetails()throws IOException {
        String prodName=productName.getText();
        String prodPrice=productPrice.getText();
        fh.writeTextFile(prodName+"||"+prodPrice, filePath);
    }

    public CartPage addProductToCart(){
        addCartButton.click();
        basketIcon.click();
        return new CartPage();
    }
}
