package com.suwasethaclinic.TestPages.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptUtility extends Utility {

    public static void scrollToJSElement(By locator){
        WebElement element=driver.findElement(locator);
        String jsScript="arguments[0].scrollIntoView()";

        ((JavascriptExecutor)driver).executeScript(jsScript,element);
    }

    // Method to click on an element using JavaScript
    public static void clickJS(By locator) {
        // Find the web element using the provided locator
        WebElement element = driver.findElement(locator);
        // Cast the driver to JavascriptExecutor
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        // Execute the JavaScript to click the element
        executor.executeScript("arguments[0].click()", element);
    }

}
