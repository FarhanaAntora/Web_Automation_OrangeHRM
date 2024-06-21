package Pages;

import Driver.PageDriver;
import Utilities.CommonMethods;
import Utilities.ExcelUtils;
import Utilities.getScreenShot;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage extends CommonMethods {
    ExtentTest test;

    ExcelUtils excelData = new ExcelUtils();

    public LoginPage(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBy(xpath = "//input[@name='username']")
    WebElement username;
    @FindBy(xpath = "//input[@name='password']")
    WebElement password;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    //Fail Case
    @SuppressWarnings("unused")
    public void failCase(String message, String scName) throws IOException {
        test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
        Throwable t = new InterruptedException("Exception");
        test.fail(t);
        String screenshotPath = getScreenShot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
        PageDriver.getCurrentDriver().quit();
    }

    @SuppressWarnings("unused")
    public void passCase(String message) {
        test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b ></p >");
    }

    @SuppressWarnings("unused")
    public void passCaseWithSC(String message, String scName) throws IOException {
        test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
        String screenshotPath = getScreenShot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }


    @SuppressWarnings("static-access")
    public void login() throws IOException {
        testDataGenerator();
        excelData.readExcelData();

        try {
            test.info("Please enter username");
            if (username.isDisplayed()) {
                username.sendKeys(excelData.username);
                passCase("Username Entered");
                Thread.sleep(2000);
                try {
                    test.info("Please enter password");
                    if (password.isDisplayed()) {
                        password.sendKeys(excelData.password);
                        passCase("Password Entered");
                        Thread.sleep(2000);
                        try {
                            test.info("Click on the login");
                            if (loginButton.isDisplayed()) {
                                loginButton.click();
                                Thread.sleep(5000);
                                passCaseWithSC("Login Successfull", "loginPass");
                            }
                        } catch (Exception e) {
                            failCase("Login button was not locatable", "loginbuttonfail");
                        }
                    }
                } catch (Exception e) {
                    failCase("Password was not locatable", "passwordfail");
                }
            }
        } catch (Exception e) {
            failCase("Usename was not locatable", "usernamefail");
        }
    }

}
