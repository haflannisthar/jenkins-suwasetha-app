package com.suwasethaclinic.TestPages.Utility;

import com.suwasethaclinic.TestPages.Base.BasePage;
import org.openqa.selenium.WebDriver;

public class Utility {

    public static WebDriver driver;

    public static void setUtilityDriver(){
        driver= BasePage.webDriver;
    }

}
