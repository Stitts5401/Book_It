package com.utilities;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface BrowserUtil
{
    String getScreenshot(String name);

    List<String> getElementsText(List<WebElement> webElementList);

    void titleVerification(String expectedTitle);

    void wait(int seconds);

    void waitForPageToLoad(long timeOutInSeconds);

    void switchToWindow(String targetTitle);

    void hover(WebElement element);

    WebElement waitForElementToBoVisible(WebElement element);

    WebElement waitForElementToBeClickable(WebElement element);

    boolean waitForUrlToContain(String string);

    List<WebElement> waitForElementsToBeVisible(List<WebElement> elements);

    boolean waitForElementToBeSelected(WebElement element);

    WebElement waitForPresenceOfElement(String locator);

    boolean waitForInvisibilityOf(WebElement element);
}
