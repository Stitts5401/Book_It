package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "email")
    private WebElement email;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    public LoginPage(){
        super();
    }

    /**
     *
     * @return the emailEle
     */
    public WebElement getEmail() {
        return getElement(email);
    }

    /**
     *
     * @param email email Box
     */
    public void setEmail(WebElement email) {
        this.email = email;
    }

    /**
     *
     * @return passwordEL
     */
    public WebElement getPassword() {
        return getElement(password);
    }


    public void setPassword(WebElement password) {
        this.password = password;
    }

    /**
     *
     * @return Login Button
     */
    public WebElement getSubmit() {
        return getElement(submit);
    }

    public void setSubmit(WebElement submit) {
        this.submit = submit;
    }

    /**
     *
     * @return get Title
     */
    public String getLoginPageTitle(){
        return getPageTitle();
    }

    /**
     *
     * @return get Url
     */
    public String getLoginPageHeader(){
        return driver.getCurrentUrl();
    }


    public HomePage doLogin(String username , String password){
        getEmail().sendKeys(username);
        getPassword().sendKeys(password);
        getSubmit().click();

        return getInstance(HomePage.class);
    }

}
