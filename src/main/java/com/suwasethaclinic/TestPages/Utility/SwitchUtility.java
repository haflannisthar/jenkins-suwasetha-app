package com.suwasethaclinic.TestPages.Utility;

import org.openqa.selenium.WebDriver;

public class SwitchUtility extends Utility{

    private static WebDriver.TargetLocator switchTo() {
        return driver.switchTo();
    }

    public static void switchToWindow(String handle) {
        switchTo().window(handle); // Switch to the window with the given handle
    }
}
