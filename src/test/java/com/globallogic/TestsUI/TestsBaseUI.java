package com.globallogic.TestsUI;

import com.globallogic.fwUI.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestsBaseUI {

    protected static ApplicationManager app = new ApplicationManager(
            System.getProperty("browser", Browser.CHROME.browserName()));

    Logger logger = LoggerFactory.getLogger(TestsBaseUI.class);

    @BeforeSuite
    public void setUp(){
        app.init();
    }

    @AfterSuite(enabled = true) //TODO set true
    public void tierDown(){
        app.stop();
    }

    @BeforeMethod
    public void startTest(Method m, Object[] p){
        logger.info("Start test " + m.getName() + " with data: " + Arrays.asList(p));
    }

    @AfterMethod
    public void stopTest(ITestResult result){
        if (result.isSuccess()) {
            logger.info("PASSED: " + result.getMethod().getMethodName());
        } else {
            logger.info("FAILED: " + result.getMethod().getMethodName() + " Screenshot: "
                    + app.getItems().takeScreenshot());
        }
        logger.info("============== Stop test =================");
    }

}
