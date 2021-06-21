/**
 * actions contains the different actions can be done through graphical user interface
 * such a clicking on elements of typing in text fields,...
 * layer or through the browser side such a window navigation forward, backward ,...
 */
package actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * this class provide different actions to perform on a web browser such a select a
 * a browser (Cross Browsing) and provide different actions of page navigation (forward, backward, refresh)
 *
 * @author Kareem Mohamed
 * @version 1.0
 * @since 18/6/2021
 */
public class BrowserActions {
    private WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * initialize the webdriver by instantiating a browser driver according to the provided
     * type of the browser [Chrome, Firefox,Ie,...]
     *
     * @param browserType specify the browser type [Chrome, Firefox,Ie,...]
     * @return the initialized webdriver
     * @throws Exception @param browserType is not a valid browser type then it throw an
     *                   Exception 'Browser type mismatch !'
     */
    public WebDriver initializeWebDriver(BrowserType browserType) throws Exception {

        switch (browserType) {
            case CHROME -> {/* Initialize Tests */
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case FIREFOX -> {

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case IE -> {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }

            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }

            case HEADLESS -> {
                WebDriverManager.phantomjs().setup();
                DesiredCapabilities dCaps = new DesiredCapabilities();
                dCaps.setJavascriptEnabled(true);
                String[] phantomJSArgs = {"--web-security=no", "--ignore-ssl-errors=yes"};
                dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomJSArgs);
                driver = new PhantomJSDriver(dCaps);
            }

            default -> {
                throw new Exception("Browser type mismatch !");
            }

        }
        return driver;

    }

    /**
     * maximize the window screen
     */
    public void maximizeScreen() {
        driver.manage().window().maximize();
    }

    /**
     * close the current open window
     */
    public void closeDriver() {
        driver.close();
    }

    /**
     * close the open windows
     */
    public void closeAllDrivers() {
        driver.quit();
    }

    /**
     * this method get a browser name from the user and return the type of it
     * according the passed name to provide readability
     *
     * @param browser browser name
     * @return the type of browser [IE, FIREFOX,...]
     * @throws Exception if the passed browser name is not valid
     *                   then it throws an exception "Not Valid Browser Type !"
     */
    public BrowserType geBrowserType(String browser) throws Exception {
        if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("chrome-headless")) {
            return BrowserType.CHROME;
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            return BrowserType.FIREFOX;
        }
        else if (browser.equalsIgnoreCase("ie")) {
            return BrowserType.IE;
        }
        else if (browser.equalsIgnoreCase("edge")) {
            return BrowserType.EDGE;
        }
        else if (browser.equalsIgnoreCase("headless")) {
            return
                    BrowserType.HEADLESS;
        }
        else
            throw new Exception("Not Valid Browser Type !");

    }

    /**
     * Navigate to the previous web page
     */
    public void goBack() {
        driver.navigate().back();
    }

    /**
     * Navigate to the next web page
     */
    public void goForward() {
        driver.navigate().forward();
    }

    /**
     * Refresh the current web page
     */
    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Browser types
     */
    public enum BrowserType {
        CHROME,
        FIREFOX,
        EDGE,
        IE,
        HEADLESS
    }
}
