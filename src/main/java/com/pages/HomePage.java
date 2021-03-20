package com.pages;

import com.utilities.Driver;

public class HomePage extends BasePage {


    public HomePage(){
        super();
    }
    public String getHeader(){
        return driver.getCurrentUrl();
    }
    public String getTitle(){
        return driver.getTitle();
    }
}
