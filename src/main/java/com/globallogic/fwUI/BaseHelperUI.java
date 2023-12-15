package com.globallogic.fwUI;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseHelperUI {

    WebDriver driver;

    public BaseHelperUI(WebDriver driver) {
        this.driver = driver;
    }

    public String takeScreenshot(){
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/screenshots/screen" + System.currentTimeMillis() + ".png");
        try{
            Files.copy(tmp, screenshot);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return screenshot.getAbsolutePath();

    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void pause (int millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
    }

    public String getElementText(By locator){
        return driver.findElement(locator).getText();
    }

    public boolean isElementPresent(By locator){
        try{
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    public void openURL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


}
