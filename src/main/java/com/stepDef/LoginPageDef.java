package com.stepDef;

import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.Page;
import com.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginPageDef {
    LoginPage page = new LoginPage();

    @Given("I enter correct Username and Password")
    public void iEnterCorrectUsernameAndPassword() {
       page.doLogin(ConfigurationReader.getProperty("username"),ConfigurationReader.getProperty("password"));

    }

    @Then("HomePage Loads")
    public void homepageLoads() {



    }
}
