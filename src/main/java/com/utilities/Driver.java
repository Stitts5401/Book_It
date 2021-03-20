package com.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Driver {

    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        if(driverPool.get() ==null){
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser){

                case "remote-chrome":
                    ChromeOptions chromeOptions1 = new ChromeOptions();
                    try {
                        URL url = new URL("http://3.89.133.216:4444/wd/hub");
                        driverPool.set(new RemoteWebDriver(url, chromeOptions1));
                    }catch (Exception e ){
                        e.printStackTrace();
                    }

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    // chromeOptions allows us to modify browser(run incognito mode, change settings etc.)
                    chromeOptions.addArguments("--start-maximized");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "chromeheadless"://headless-->browser with no interface. so browser is running in the background
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    //if browser type, stop test and throw exception
                    throw new RuntimeException("Wrong browser type");

            }
        }
        return driverPool.get();
    }


    public static void close() {
        if(driverPool!=null){
            //close all browsers
            driverPool.get().quit();
            //destroy driver object , ready fo garbage collection
            driverPool.remove();
        }
    }
}
