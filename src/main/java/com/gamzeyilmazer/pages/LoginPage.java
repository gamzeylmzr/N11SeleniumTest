package com.gamzeyilmazer.pages;

import com.gamzeyilmazer.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

public class LoginPage  extends TestBase{

    //Page Factory
    @FindBy(id="email")
    WebElement emailTxt;

    @FindBy(id="pass")
    WebElement passwordTxt;

    @FindBy(id="loginbutton")
    WebElement loginBtn;

    @FindBy(className = "btnLogin")
    WebElement faceLoginBtn;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    public HomePage login(String mail, String pass){
        faceLoginBtn.click();
        String MainWindow = driver.getWindowHandle();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            String ChildWindow = i1.next();

            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                emailTxt.sendKeys(mail);
                passwordTxt.sendKeys(pass);
                loginBtn.click();
            }
        }
        driver.switchTo().window(MainWindow);
        return new HomePage(); //after login, it will directly navigate to homepage so it should return HomePage
    }
}
