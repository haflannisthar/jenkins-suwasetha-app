package com.suwasethaclinic.TestPages.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility extends Utility{

    public static void explicitWait(By locator,int seconds){

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(seconds));
     wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }


    public static void fluentWait(By locator,int seconds){
        Wait<WebDriver> fluentWait=new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchFieldException.class, StaleElementReferenceException.class);

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
