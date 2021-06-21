package base_tests;

import actions.BrowserActions;
import actions.UI_Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import utils.Reporting;
import utils.files.PropertiesFileManager;

import java.net.URL;

public class BaseTests_SeleniumGrid {
    protected ThreadLocal<WebDriver> driver = null ;
    protected BrowserActions browserActions;
    protected UI_Actions uiActions;
    protected HomePage homePage;

    /* Instantiate instance form Logger to log the execution */
    Logger logger = LogManager.getLogger(BaseTests_SeleniumGrid.class);

    /**
     * set up the web browser before starting the executions of testcases
     *
     * @param browserName specify the browser name from an external file
     */
    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("Chrome") String browserName) {
        try {

            driver = new ThreadLocal<>() ;
            DesiredCapabilities caps = new DesiredCapabilities() ;
            caps.setCapability("browserName",browserName);
            driver.set(new RemoteWebDriver(new URL(new PropertiesFileManager("src/main/resources/Configurations/baseTests_SeleniumGrid.properties").getPropertyValue("SELENIUM_GRID_URL")),caps));
            browserActions = new BrowserActions(driver.get());
            uiActions = new UI_Actions(driver.get());
            homePage = new HomePage(driver.get());
            browserActions.maximizeScreen();
            homePage.goToHome();
            logger.info("Open a new browser instance");

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.info("Can not instantiate a driver instance");

        }

    }

    /**
     * Before each testcase start from the home page to make each testcase independent
     * of the other ones
     */
    @BeforeMethod
    public void StartFromHomePage() {
        try {
            homePage.goToHome();
            logger.info("Start from home page for each testcase");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.info("Can not navigate to home page ");
            Assert.fail();
        }
    }

    /**
     * After each testcase if the testcase failed it should take an screenshot of the execution
     *
     * @param result specify the result of the testcase if it failed or passed to take
     *               the correct actiosn
     */
    @AfterMethod
    public void getFailureScreenShot(ITestResult result) {
        Reporting.recordFailure(result, driver.get());
    }

    /**
     * After executing the testcases close all  opened windows
     */
    @AfterClass
    public void tearDown() {
        browserActions.closeAllDrivers();
    }
}
