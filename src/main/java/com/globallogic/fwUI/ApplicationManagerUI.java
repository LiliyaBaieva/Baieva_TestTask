package com.globallogic.fwUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManagerUI {

    String browser;
    WebDriver driver;
    ItemsHelperUI items;

    public static final String BASE_URL = "http://demowebshop.tricentis.com";

    public ApplicationManagerUI(String browser) {
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

        items = new ItemsHelperUI(driver);

    }

    public ItemsHelperUI getItems() {
        return items;
    }

    public void stop(){
        driver.quit();
    }



}
