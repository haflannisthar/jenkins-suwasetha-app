package com.suwasethaclinic.TestPages.Utility;

public class GetUtility extends Utility{

    public static String getURL(){
       return driver.getCurrentUrl();
    }

    public static String getTitle(){
        return driver.getTitle();
    }
}
