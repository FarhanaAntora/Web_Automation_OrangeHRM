package Pages;

import Driver.PageDriver;
import Utilities.getScreenShot;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class AddEmployee {
    ExtentTest test;

    public AddEmployee(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBy(xpath = "//span[text()='PIM']")
    WebElement pim;
    @FindBy(xpath = "//a[text()='Add Employee']")
    WebElement addEmployee;
    @FindBy(xpath = "//input[@name='firstName']")
    WebElement firstname;
    @FindBy(xpath = "//input[@name='lastName']")
    WebElement lastname;
    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
    WebElement employeeId;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement save;

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

    public void PIM() throws IOException {

        try {
            test.info("Click on PIM");
            if (pim.isDisplayed()) {
                pim.click();
                Thread.sleep(5000);
                passCaseWithSC("Clicked", "pimPass");
                try {
                    test.info("Click on AddEmployee");
                    if (addEmployee.isDisplayed()) {
                        addEmployee.click();
                        passCase("AddEmployee Clicked");
                        Thread.sleep(5000);
                        try {
                            test.info("Enter Firstname");
                            if (firstname.isDisplayed()) {
                                firstname.sendKeys("Jenny");
                                Thread.sleep(5000);
                                passCase("Firstname Entered");
                                try {
                                    test.info("Enter Lasttname");
                                    if (lastname.isDisplayed()) {
                                        lastname.sendKeys("Rahman");
                                        Thread.sleep(5000);
                                        passCase("Lastname Entered");
                                        try {
                                            test.info("Enter Employee ID");
                                            if (employeeId.isDisplayed() ) {
                                                Thread.sleep(5000);
                                                employeeId.sendKeys("015894");
                                                Thread.sleep(5000);
                                                passCase("Employee Id Entered");
                                                Thread.sleep(5000);
                                                try {
                                                    test.info("Click on save");
                                                    if (save.isDisplayed()) {
                                                        save.click();
                                                        Thread.sleep(5000);
                                                        passCase("Clicked on Save");
                                                    }
                                                }
                                                catch (Exception e) {
                                                    failCase("Save button was not locatable", "savebuttonfail");
                                                }
                                            }
                                        } catch (Exception e) {
                                            failCase("Employee ID was not locatable", "employeeIDFail");
                                        }
                                    }
                                } catch (Exception e) {
                                    failCase("Lastname was not locatable", "lastnameFail");
                                }
                            }

                        } catch (Exception e) {
                            failCase("Firstname was not locatable", "firstnameFail");
                        }
                    }
                } catch (Exception e) {
                    failCase("AddEmployee was not locatable", "addEmployeeFail");
                }
            }
        } catch (Exception e) {
            failCase("PIM was not locatable", "pimeFail");
        }
    }

}
