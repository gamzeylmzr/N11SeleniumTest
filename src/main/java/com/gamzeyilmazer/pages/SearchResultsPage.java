package com.gamzeyilmazer.pages;

import com.gamzeyilmazer.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends TestBase {

    @FindBy(id="searchData")
    WebElement searchTxt;

    @FindBy(className="searchBtn")
    WebElement searchBtn;

    @FindBy(linkText = "2")
    WebElement secondPage;

    @FindBy(xpath = "//*[@id='view']/ul/li[1]")
    WebElement firstProduct;


    public SearchResultsPage(){
        PageFactory.initElements(driver,this);
    }

    public SearchResultsPage searchByGivenText(String search_text){
        searchTxt.sendKeys(search_text);
        searchBtn.click();
        return new SearchResultsPage();
    }

    public boolean isAnyResultFound() {
        return (driver.findElements(By.className("notFoundContainer")).size() != 0);
    }

    public SearchResultsPage navigateToSecondPage(){
        Actions actions = new Actions(driver);
        actions.moveToElement(secondPage);
        actions.perform();
        secondPage.click();
        return new SearchResultsPage();
    }

    public String getPageNumber() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.substring(currentUrl.lastIndexOf('=')+1);
    }

    public ProductPage chooseProduct(){
        Actions actions = new Actions(driver);
        actions.moveToElement(firstProduct);
        actions.perform();
        firstProduct.click();
        return new ProductPage();
    }
}
