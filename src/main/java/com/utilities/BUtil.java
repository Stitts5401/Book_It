package com.utilities;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BUtil implements BrowserUtil{

    @Override
    public String getScreenshot(String name) {
        var path = System.getProperty("user.dir")+"/test-output/screenshots/"+name+".png";
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
        return null;
    }

    @Override
    public List<String> getElementsText(List<WebElement> webElementList) {
        return null;
    }

    @Override
    public void titleVerification(String expectedTitle) {

    }

    @Override
    public void wait(int seconds) {

    }

    @Override
    public void waitForPageToLoad(long timeOutInSeconds) {

    }

    @Override
    public void switchToWindow(String targetTitle) {

    }

    @Override
    public void hover(WebElement element) {

    }

    @Override
    public WebElement waitForElementToBoVisible(WebElement element) {
        return null;
    }

    @Override
    public WebElement waitForElementToBeClickable(WebElement element) {
        return null;
    }

    @Override
    public boolean waitForUrlToContain(String string) {
        return false;
    }

    @Override
    public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
        return null;
    }

    @Override
    public boolean waitForElementToBeSelected(WebElement element) {
        return false;
    }

    @Override
    public WebElement waitForPresenceOfElement(String locator) {
        return null;
    }

    @Override
    public boolean waitForInvisibilityOf(WebElement element) {
        return false;
    }
}
