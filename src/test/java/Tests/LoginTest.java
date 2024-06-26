package Tests;

import Driver.BaseDriver;
import Driver.PageDriver;
import Pages.LoginPage;
import Utilities.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseDriver {
    ExtentReports report;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void start() throws InterruptedException {
        PageDriver.getCurrentDriver().get(url);
        Thread.sleep(5000);
        report = ExtentFactory.getInstance();
        parentTest = report.createTest("<p style=\"color:#FF6000; font-size:20px\"><b>ORANGE HRM LOGIN</b></p>").assignAuthor("FARHANA").assignDevice("Windows");
    }
    @Test
    public void LoginTest() throws IOException{
        childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:20px\"><b>LOGIN</b></p>");
        LoginPage loginPage = new LoginPage(childTest);
        loginPage.login();
    }
    @AfterClass
    public void report() {
        report.flush();
    }

}


