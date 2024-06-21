package Pages;

import Driver.PageDriver;
import Utilities.getScreenShot;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class DashboardPage {

    ExtentTest test;

    public DashboardPage(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBy(xpath = "//span[text()='Admin']")
    WebElement admin;
    @FindBy(xpath = "//span[text()='Recruitment']")
    WebElement recruit;
    @FindBy(xpath = "//span[text()='Performance']")
    WebElement performance;

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
        test.pass("<p style=\"color:#85BC63; font-size:13 px\"><b>" + message + "</b ></p >");
        String screenshotPath = getScreenShot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    public void admin() throws IOException {
        try {
            test.info("Click on admin");
            if (admin.isDisplayed()) {
                admin.click();
                Thread.sleep(5000);
                passCaseWithSC("Clicked", "adminPass");
            }
        } catch (Exception e) {
            failCase("Admin was not locatable","adminfail");
        }
    }

    public void Recruit() throws IOException {
        try {
            test.info("Click on Recruitment");
            if (recruit.isDisplayed()) {
                recruit.click();
                Thread.sleep(5000);
                passCaseWithSC("Clicked", "recruitmentPass");
            }
        } catch (Exception e) {
            failCase("Recruitment was not locatable","recruitmentfail");
        }
    }

    public void Performance() throws IOException {
        try {
            test.info("Click on Performance");
            if (performance.isDisplayed()) {
                performance.click();
                Thread.sleep(5000);
                passCaseWithSC("Clicked", "performancePass");
            }
        } catch (Exception e) {
            failCase("Performance was not locatable","performanceFail");
        }
    }

}




