package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page{
    public BasePage(){
        super();
    }

    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Override
    public String getPageHeader(WebElement locator) {
        return getElement(locator)
                .getText();
    }

    @Override
    public WebElement getElement(WebElement locator) {
       WebElement element = null;
       try{
           element = locator;
           return  element;
       } catch (Exception e){
           System.out.println("Some error occurred while creating element "+ locator.toString());
           e.printStackTrace();
       }
       return  element;

    }

    @Override
    public void waitForElementPresent(By locator) {
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        }catch (Exception e ){
            e.printStackTrace();
            System.out.println("Some exception/error occurred while waiting for the element "+locator.toString());
        }
    }

    @Override
    public void waitForPageTitle(String title) {
        try{
            wait.until(ExpectedConditions.titleContains(title));
        }
        catch (Exception e){
            System.out.println("Some exception/error occurred while waiting for the element "+ title);
        }
    }

}
