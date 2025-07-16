package com.suwasethaclinic.Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.suwasethaclinic.TestPages.Base.BasePage;
import com.suwasethaclinic.TestPages.Suwastha.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;


import java.io.File;
import java.io.IOException;

import static com.suwasethaclinic.TestPages.Utility.Utility.setUtilityDriver;

public class BaseTest {

    private WebDriver webDriver;

    protected BasePage basePage;
    protected LoginPage loginPage;


    protected ExtentReports extent;
    protected ExtentTest test;

    private String appURL="http://localhost:8081/";

    @BeforeClass
    public void setUp(){
     webDriver=new ChromeDriver();
     webDriver.manage().window().maximize();

        extent = ExtentReportManager.getInstance();

    }

    @BeforeMethod
    public void loadApplication(Method method){
        webDriver.get(appURL);
        basePage=new BasePage();
        basePage.setWebDriver(webDriver);
        setUtilityDriver();
        loginPage=new LoginPage();



        test = extent.createTest(method.getName());


    }

    @AfterMethod
    public void takeFailTestScreenShot(ITestResult iTestResult) {
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            test.fail(iTestResult.getThrowable());

            TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
            File source = takesScreenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = java.time.LocalDateTime.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
            String fileName = String.format("screenshot_%s_(%s).png", timestamp, iTestResult.getName());

            // Save in test-output/screenshots/
            String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
            new File(screenshotDir).mkdirs(); // Create dir if not exist

            String fullPath = screenshotDir + fileName;
            String relativePath = "screenshots/" + fileName; // relative to ExtentReport.html

            File destination = new File(fullPath);

            try {
                FileHandler.copy(source, destination);
                // Use relative path in report
                test.addScreenCaptureFromPath(relativePath);
            } catch (IOException e) {
                test.warning("Could not attach screenshot due to IO error");
            }

            System.out.println("Screenshot saved to: " + fullPath);
        }

        extent.flush();
    }


    // Tear down method that runs after all test methods in the class
    @AfterClass
    public void tearDown() throws InterruptedException {
        // Sleep for a short duration before closing the browser
        Thread.sleep(2000);
        webDriver.quit(); // Close the browser and end the WebDriver session
    }

}
