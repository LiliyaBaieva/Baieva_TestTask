package com.globallogic.fwUI;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {

    String browser;
    WebDriver driver;
    ItemsHelper items;

    public static final String BASE_URL = "http://demowebshop.tricentis.com";

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init () {

        if(browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")){
            EdgeOptions options = new EdgeOptions();
            options.addArguments("remote-allow-origins=*");
            driver = (WebDriver) new EdgeDriver();
        }

        items = new ItemsHelper(driver);

    }

    public ItemsHelper getItems() {
        return items;
    }

    public void stop(){
        driver.quit();
    }



}
