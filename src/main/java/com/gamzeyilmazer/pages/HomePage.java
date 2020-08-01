package com.gamzeyilmazer.pages;

import com.gamzeyilmazer.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    @FindBy(className = "btnSignIn")
    WebElement signInLink;

    @FindBy(css = "a.menuLink.user")
    WebElement username;

    @FindBy(css = "[title*='hayat sana gelir']")
    WebElement logo;

    public HomePage(){
        PageFactory.initElements(driver,this);
    }

    public boolean isHomePageLogoDisplayed(){
        return logo.isDisplayed();
    }


    public void clickOutside() {
        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();
    }

    public String isUsernameDisplayed(){
        return username.getText();
    }

    public LoginPage navigateToLoginPage(){
        clickOutside();
        signInLink.click();
        return new LoginPage();
    }


}
