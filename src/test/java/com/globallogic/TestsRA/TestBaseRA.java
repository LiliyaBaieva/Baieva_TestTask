package com.globallogic.TestsRA;

import com.globallogic.fwRA.ApplicationManagerRA;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBaseRA {

    protected static ApplicationManagerRA appRA = new ApplicationManagerRA();
    final static Logger logger = LoggerFactory.getLogger(TestBaseRA.class);
    protected static String token = "";

    @BeforeSuite
    public void setUp(){
        appRA.init();
    }

    @BeforeMethod
    public void precondition(Method method, Object[] parameters){
        RestAssured.baseURI = appRA.BASE_URL;
        logger.info("Start test " + method.getName() + " with parameters " + Arrays.asList(parameters));
    }

    @AfterMethod
    public void quit(ITestResult result){
        if(result.isSuccess()){
            logger.info("PASSED: " + result.getMethod().getMethodName());
        } else {
            logger.info("FAILED: " + result.getMethod().getMethodName());
        }
        logger.info("=============== Stop test ===============");
    }

}



