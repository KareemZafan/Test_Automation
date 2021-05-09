package php_resgisration;

import ddt.CsvFileManager;
import ddt.ExcelFileManager;
import ddt.JSONFileManager;
import ddt.PropertiesFileManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;


@Test
public class HpTravelTests {

    private final By welcomeMessageLocator = By.xpath("//h3[@class='text-align-left']");
    private final String BASE_URL= "https://phptravels.net/home";
    private final String SAUCE_LABS_URL = PropertiesFileManager.loadPropertiesData("src/main/resources/SauceLabsUser/SauceLabsURL.properties").getProperty("sauceLabsURL") ;
    ThreadLocal<WebDriver> driver = null ;

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) throws Exception {

           /* Headless Testing */
          /*DesiredCapabilities dCaps = new DesiredCapabilities();
            dCaps.setJavascriptEnabled(true);
            dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "resources/phantomjs.exe");
            String[] phantoJSArgs = {"--web-security=no", "--ignore-ssl-errors=yes"};
            dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantoJSArgs);
            driver = new PhantomJSDriver(dCaps);*/

        driver = new ThreadLocal<>() ;
       DesiredCapabilities caps = new DesiredCapabilities() ;
       caps.setCapability("browserName",browser);
       /* for working from locally in your localhost:4444 Local Selenium Grid */
      /* driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps));*//* driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps));*/
       /* Global [Remote Selenium Grid ] SauceLabs */
        driver.set(new RemoteWebDriver(new URL(SAUCE_LABS_URL),caps));

       getDriver().manage().window().maximize();
       getDriver().get(BASE_URL);

    }


    protected WebDriver getDriver(){
        /* return the the driver thread */
        return driver.get();
    }

    @Test(dataProvider = "readCsvDataFile")
    public void testSignUp(String f_Name, String l_Name, String phoneNum, String email, String password, String confirmPassword) {

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        /* Test Case Steps */
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.linkText("MY ACCOUNT")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.linkText("Sign Up")))).click();
        /* driver */
        getDriver().findElement(By.name("firstname")).sendKeys(f_Name);
        getDriver().findElement(By.name("lastname")).sendKeys(l_Name);
        getDriver().findElement(By.name("phone")).sendKeys(phoneNum);
        getDriver().findElement(By.name("email")).sendKeys(email);
        getDriver().findElement(By.name("password")).sendKeys(password);
        getDriver().findElement(By.name("confirmpassword")).sendKeys(confirmPassword);
        /* Sign up button element locator */
        WebElement submit_btn = getDriver().findElement(By.xpath("//button[@class='signupbtn btn_full btn btn-success btn-block btn-lg']"));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("scroll(0,250)");
        wait.until(ExpectedConditions.elementToBeClickable(submit_btn)).click();

        String WelcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessageLocator)).getText();
        /* Generic assertion for all users */
        Assert.assertEquals(WelcomeMessage, "Hi, " + f_Name + " " + l_Name);

        /*logout after signing up for each user */
        getDriver().findElement(By.linkText(f_Name.toUpperCase())).click();
        getDriver().findElement(By.linkText("Logout")).click();
    }

    @AfterClass
    public void tearDown() {
       getDriver().quit();
       driver.remove();
    }

    @DataProvider
    public Object[][] readExcelSheet() throws IOException, InvalidFormatException {
        return ExcelFileManager.getExcelData("test_data/Account_Credentials.xlsx","Sheet1");
    }

    @DataProvider
    public Object[][] readCsvDataFile() {
        try {
            return CsvFileManager.getCsvData("test_data/CsvFileData.csv");
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }

    }

    @DataProvider
    public Object[][] readJsonDataFile() {
        String keys[] = {"firstName", "lastName", "mobile", "email", "password", "confirmPassword"};

        try {
            return JSONFileManager.readJsonData("test_data/credentials.json", keys);
        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    /**
     * Notice i tried to use 1D array to return the data of the properties file but
     * selenium does not support to return 1D array you have to use Object [][] array or
     * iterator , search for it ...
     *
     * @return Object[][] array represents the user credentials
     */
    @DataProvider
    public Object[][] readPropertiesDataFile() {
        String keys[] = {"firstName", "lastName", "mobile", "email", "password", "confirmPassword"};
        return PropertiesFileManager.getProperties("test_data/data.properties", keys, 1);
    }
}
