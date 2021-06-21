package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


/**
 * Report the text execution and provide attachments to proove the tests status
 * using Allure Reporting and generation HTML Report.
 *
 * @author Kareem Mohamed
 * @version 1.0
 * @since 18/6/2021
 */
public class Reporting {

    /**
     * Take screenshots when the testcase failed
     *
     * @param testResult specify the test result if failed or passed to take the correct action
     * @param driver specify the webdriver to bed used with allure reporting to take screenshots
     */
    public static void recordFailure(ITestResult testResult, WebDriver driver) {
        try {
            if (ITestResult.FAILURE == testResult.getStatus()) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.moveFile(scrFile, new File("screenshots/" + testResult.getName() + ".png"));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
