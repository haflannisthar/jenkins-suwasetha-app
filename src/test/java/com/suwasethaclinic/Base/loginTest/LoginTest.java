package com.suwasethaclinic.Base.loginTest;

import com.suwasethaclinic.Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @Test
    public void appSuccessLoginTest(){
        loginPage.setUserName("Admin");
        loginPage.setPassword("12345");
        loginPage.clickBtn();

        String expectedResult="Quick";
        String actualResult= loginPage.getSuccessLoginMessage();

        Assert.assertTrue(actualResult.contains(expectedResult),"Login success");
    }

    @Test
    public void appFailedLoginTest(){
        loginPage.setUserName("Admin");
        loginPage.setPassword("123456");
        loginPage.clickBtn();

        String expectedResult="http://localhost:8081/login?error=usernamepassworderror";
        String actualResult= loginPage.getErrorURL();

        System.out.println("Actual Result: " + actualResult);

        Assert.assertEquals(actualResult, expectedResult, "Login failed");
    }






}
