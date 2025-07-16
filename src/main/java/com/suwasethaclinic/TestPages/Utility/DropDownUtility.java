package com.suwasethaclinic.TestPages.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DropDownUtility extends Utility{

    private static Select findByDropDown(By locator){
        return  new Select(driver.findElement(locator));
    }

   public static void selectByVisibleText(By locator,String text){
        findByDropDown(locator).selectByVisibleText(text);
   }

    public static void selectByIndex(By locator,int index){
        findByDropDown(locator).selectByIndex(index);
    }

    public static void selectByValue(By locator,String value){
        findByDropDown(locator).selectByValue(value);
    }

}
