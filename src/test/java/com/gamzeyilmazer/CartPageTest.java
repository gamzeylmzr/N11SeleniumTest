package com.gamzeyilmazer;

import com.gamzeyilmazer.base.TestBase;
import com.gamzeyilmazer.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends TestBase{

    CartPage cartPage;

    public CartPageTest(){
        super();
        cartPage=new CartPage();
    }

    @Test(priority = 1)
    public void verifyPricesProductAndCartPages(){
        Assert.assertEquals(cartPage.getPriceFromCartPage(),cartPage.getPriceFromText());
    }

    @Test(priority = 2)
    public void increaseAndVerifyQuantity(){
        cartPage.increaseQuantity();
        Assert.assertEquals(cartPage.getQuantity(),"2");
    }

    @Test(priority =3)
    public void deleteProductFromCart(){
        cartPage.deleteProduct();
        Assert.assertEquals(cartPage.isProductDeleted(),true);
    }
}
