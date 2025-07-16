package com.suwasethaclinic.TestPages.Suwastha;

import com.suwasethaclinic.TestPages.Base.BasePage;
import org.openqa.selenium.By;

import static com.suwasethaclinic.TestPages.Utility.Utility.driver;
import static com.suwasethaclinic.TestPages.Utility.WaitUtility.fluentWait;

public class LoginPage extends BasePage {


    private final By userNameField=By.id("textUsername");
    private final By passwordField=By.id("textPassword");
    private final By loginButton=By.xpath("//input[@type='submit' and @value='Sign Up']");
    private final By quickAccessTitle=By.xpath("/html/body/div[2]/div[1]/div/div/div/h5");
    private final By loginErrorTitle=By.xpath("//span[@class='error-msg']");

    public void setUserName(String Username){
        set(userNameField,Username);
    }

    public void setPassword(String password){
        set(passwordField,password);
    }

    public  void clickBtn(){
        click(loginButton);
    }
    public String getSuccessLoginMessage(){
      return   find(quickAccessTitle).getText();
    }





    public String getFailedLoginMessage(){
         fluentWait(loginErrorTitle,10);
        return find(loginErrorTitle).getText();
    }

    public String getErrorURL(){
        return driver.getCurrentUrl();
    }



    public DashboardPage loginToApp(String username,String password){

        set(userNameField,username);
        set(passwordField,password);
        click(loginButton);
        return new DashboardPage();

    }


}
