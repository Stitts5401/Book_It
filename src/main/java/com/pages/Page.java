package com.pages;

import com.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page extends Driver {
    WebDriverWait wait;
    WebDriver driver = Driver.getDriver();
   public Page(){
        PageFactory.initElements(this.driver,this);
        this.wait = new WebDriverWait(this.driver, 15);
    }

    //Abstract Methods
    public abstract String getPageTitle();

    public abstract String getPageHeader(WebElement locator);

    public abstract WebElement getElement(WebElement locator);

    public abstract void waitForElementPresent(By locator);

    public abstract void waitForPageTitle(String title);

    public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {

        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
        
    }


}
