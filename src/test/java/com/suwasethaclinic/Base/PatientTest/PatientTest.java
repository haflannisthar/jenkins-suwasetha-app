package com.suwasethaclinic.Base.PatientTest;

import com.suwasethaclinic.Base.BaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.suwasethaclinic.TestPages.Utility.GetUtility.getTitle;
import static com.suwasethaclinic.TestPages.Utility.GetUtility.getURL;
import static com.suwasethaclinic.TestPages.Utility.Utility.driver;


public class PatientTest extends BaseTest {

    @Test
    public void savePatientSuccessTest() {
        var patientPage = loginPage.loginToApp("Admin", "12345").clickPatientPage();
        patientPage.clickPatientForm();
        patientPage.selectPatientTitle("Mr");
        patientPage.setPatientFirstName("Marhoom");
        patientPage.setPatientLastName("Manga");

        patientPage.setPatientBOD("15102001");

        patientPage.selectPatientGender(2);
        patientPage.setPatientContactNo("0777267756");
        patientPage.clickPatientSaveBtn();
        patientPage.clickConfirmButton();


       String successText=patientPage.getSuccessText();
       String expectedText="Successfully";

       Assert.assertTrue(successText.contains(expectedText),"\n expected text and actual text does not match test failed");

      if (successText.contains(expectedText)){
          System.out.println("Test Passed");
      }else{
          System.out.println("Test Failed");
      }



    }


    @Test
    public void savePatientFailureTest() {
        var patientPage = loginPage.loginToApp("Admin", "12345").clickPatientPage();
        patientPage.clickPatientForm();
        patientPage.selectPatientTitle("Mr");
        patientPage.setPatientFirstName("Marhoom");
        patientPage.setPatientLastName("Ma");

        patientPage.setPatientBOD("15102001");

        patientPage.selectPatientGender(2);
        patientPage.setPatientContactNo("0177267756");
        patientPage.clickPatientSaveBtn();

      String actualErrorMessage=patientPage.getErrorMessage();
        System.out.println(actualErrorMessage);

        String expectedErrorText="Errors";

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorText),"\n expected text and actual text does not match test failed");

        if (actualErrorMessage.contains(expectedErrorText)){
            System.out.println("Test Passed");
        }else{
            System.out.println("Test Failed");
        }



    }


    @Test
    public void printPatientDetails(){
        var patientPage = loginPage.loginToApp("Admin", "12345").clickPatientPage();
        patientPage.patientTableDropDownClick("PR000054");
        patientPage.patientDropDownPrintClick("PR000054");
        patientPage.newWindow();



        String actualURL= getURL();
        System.out.println(actualURL);
        String expectedURL="about:blank";
        Assert.assertEquals(actualURL,expectedURL,"\n actual and expected url doesnt match \n");


//
//        String actualTitle= getTitle();
//        System.out.println(actualTitle);
//        String expectedTitle="Patient Print";
//        Assert.assertEquals(actualTitle,expectedTitle,"\n actual and expected Title doesnt match \n");

    }






}
