package base_tests;

import actions.BrowserActions;
import actions.UI_Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import utils.KeywordDriven;
import utils.Reporting;

/**
 * This class contains the pre-requisites  related to each test such a driver setUp and and closing driver and
 * different test listeners , and  it will be the base of each test class to start from
 *
 * @author Kareem Mohamed
 * @version 1.0
 * @since 18/6/2021
 */
public class BaseTests {
    protected WebDriver driver;
    protected BrowserActions browserActions;
    protected UI_Actions uiActions;
    protected HomePage homePage;
    protected KeywordDriven keywordDriven ;
    /* Instantiate instance form Logger to log the execution */
    Logger logger = LogManager.getLogger(BaseTests.class);

    /**
     * set up the web browser before starting the executions of testcases
     *
     * @param browserName specify the browser name from an external file
     */
    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("Chrome") String browserName) {
        try {

            browserActions = new BrowserActions(driver);
            driver = browserActions.initializeWebDriver(browserActions.geBrowserType(browserName));
            uiActions = new UI_Actions(driver);
            homePage = new HomePage(driver);
            keywordDriven = new KeywordDriven(driver) ;
            browserActions.maximizeScreen();
            homePage.goToHome();
            logger.info("Open a new browser instance");

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.info("Can not instantiate a driver instance");
            Assert.fail();
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
        Reporting.recordFailure(result, driver);
    }

    /**
     * After executing the testcases close all  opened windows
     */
    @AfterClass
    public void tearDown() {
        browserActions.closeAllDrivers();
    }


}
