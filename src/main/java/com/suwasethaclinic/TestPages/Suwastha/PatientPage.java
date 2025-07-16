package com.suwasethaclinic.TestPages.Suwastha;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

import static com.suwasethaclinic.TestPages.Utility.DropDownUtility.*;
import static com.suwasethaclinic.TestPages.Utility.JavaScriptUtility.scrollToJSElement;
import static com.suwasethaclinic.TestPages.Utility.SwitchUtility.switchToWindow;
import static com.suwasethaclinic.TestPages.Utility.WaitUtility.fluentWait;

public class PatientPage extends DashboardPage{


    private final By patientFormTab=By.xpath("//a[@href='#PatientForm']");
    private final By patientTitle=By.id("selectTitle");
    private final By patientFirstName=By.id("textFirstName");
    private final By patientLastName=By.id("textLastName");
    private final By patientBOD=By.id("textDateOfBirth");
    private final By patientGender=By.id("selectGender");
    private final By patientContactNo=By.id("textContactNumber");
    private final By patientEmergencyName=By.id("textEmergencyName");
    private final By patientEmergencyContactNo=By.id("textEmergencyContactNo");
    private final By patientSubmitBtn=By.id("btnAddPat");
    private final By swalFireVisibility=By.cssSelector(".swal2-popup");
    private final By swalFireConfirmButton=By.cssSelector(".swal2-confirm");
    private final By swalFireTitleText =By.cssSelector(".swal2-title");

    private final By patientTable=By.id("tablePatient");
    private final By validationPatNo=By.id("tdPatNumber");







    public void clickPatientForm(){
        click(patientFormTab);

    }
    public void selectPatientTitle(String title){
        scrollToJSElement(patientTitle);
        selectByVisibleText(patientTitle,title);
    }

    public void setPatientFirstName(String firstName){
        scrollToJSElement(patientFirstName);
        set(patientFirstName,firstName);
        set(patientEmergencyName,firstName);

//        find(patientFirstName).sendKeys(firstName);
//        find(patientEmergencyName).sendKeys(firstName);
    }
    public void setPatientLastName(String lastName){
       set(patientLastName,lastName);
    }


    public void setPatientBOD(String dateOfBirth){
        ((JavascriptExecutor)driver).executeScript ("document.getElementById('textDateOfBirth').removeAttribute('onkeydown');");
        click(patientBOD);
        set(patientBOD, dateOfBirth);
        find(patientBOD).sendKeys(Keys.TAB);

    }


    public String getCalVal(){
        set(patientBOD,"2001-10-16");
        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.TAB);
        return find(patientContactNo).getAttribute("value");
    }

    public void selectPatientGender(int index){
        selectByIndex(patientGender,index);
    }

    public void setPatientContactNo(String number){
        set(patientContactNo,number);
        set(patientEmergencyContactNo,number);

    }

    public void clickPatientSaveBtn(){
        click(patientSubmitBtn);
    }


    public void clickConfirmButton(){
        fluentWait(swalFireVisibility,5);
        click(swalFireConfirmButton);
    }


    public String getSuccessText(){
        fluentWait(swalFireVisibility,5);
        return  find(swalFireTitleText).getText();
    }

        public String getErrorMessage(){
            fluentWait(swalFireVisibility,5);
            return  find(swalFireTitleText).getText();
    };


    public void patientTableDropDownClick( String patientID){
        fluentWait(patientTable,5);
         By tableDropDownMenu=By.xpath("//table[@id='tablePatient']//tbody/tr[td[2]='"+patientID+"']//i");
         click(tableDropDownMenu);

    }


    public void patientDropDownPrintClick(String patientID){
        By printButton=By.xpath("//table[@id='tablePatient']//tbody/tr[td[2]='"+patientID+"']//li//button[text()='Print']");
        click(printButton);
        fluentWait(swalFireVisibility,5);
        click(swalFireConfirmButton);
    }

    public void newWindow(){

        String currentWindow=driver.getWindowHandle();
        System.out.println("Main window id  : "+ currentWindow +"\n");

        Set<String> allWindows=driver.getWindowHandles();
        System.out.println("all window handle : " +allWindows.size());

        for (String handle:allWindows){
            if (currentWindow.equals(handle)){
                System.out.println("1st window id " +handle);
            }else{
                switchToWindow(handle);  // == driver.switchTo.window(handle) --> all this utility methods are written in utility package
                System.out.println("2nd window id : " +handle);
            }
        }



    }

   



}
