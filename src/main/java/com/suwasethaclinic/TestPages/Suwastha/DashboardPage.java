package com.suwasethaclinic.TestPages.Suwastha;

import org.openqa.selenium.By;

public class DashboardPage extends LoginPage{

   private final By patientCard=By.xpath("//div[@class='col-1 text-start']//button//a[@href='/patient']");


   public PatientPage clickPatientPage(){
       click(patientCard);
       return new PatientPage();
   }


}
