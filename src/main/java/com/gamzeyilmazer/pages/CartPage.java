package com.gamzeyilmazer.pages;

import com.gamzeyilmazer.base.TestBase;
import com.gamzeyilmazer.helpers.FileHelpers;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends TestBase{

    FileHelpers fh=new FileHelpers();
    String price;
    private String filePath=System.getProperty("user.dir")+"/src/test/resources/proDetails.txt";

    @FindBy(className = "priceArea")
    WebElement selectedProPrice;

    @FindBy(xpath = "//span[contains(text() , '+')]")
    WebElement spinnerUpBtn;

    @FindBy(name = "quantity")
    WebElement quantityText;

    @FindBy(xpath = "//span[contains(text() , 'Sil')]")
    WebElement deleteIcon;

    @FindBy(className = "cartEmptyText")
    WebElement emptyCart;

    public CartPage(){
        PageFactory.initElements(driver,this);
    }

    public String getPriceFromText(){
        try{
            StringBuffer sb =fh.readTextFile(filePath);
            price=sb.toString();
            price=price.substring(price.lastIndexOf('|')+1);

        }
        catch (Exception e){e.printStackTrace();}
        System.out.println(price);
        return price;
    }

    public String getPriceFromCartPage(){
        return selectedProPrice.getText();
    }

    public CartPage increaseQuantity(){
        spinnerUpBtn.click();
        return new CartPage();
    }

    public String getQuantity(){
        return quantityText.getAttribute("value");
    }

    public void deleteProduct(){
        deleteIcon.click();
        driver.navigate().refresh();//to view empty cart

    }
    public boolean isProductDeleted(){
        return emptyCart.isDisplayed();
    }
}
