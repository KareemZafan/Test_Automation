package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class LoginStepDefinitions {
   private WebDriver driver ;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver() ;
        driver.manage().window().maximize();

    }

    @Given("User open the website and go to login page")
    public void user_open_the_website_and_go_to_login_page() {
        driver.get("https://classic.crmpro.com");
    }
    @When("User fill email as {string} and {string} and click on login")
    public void user_fill_the_email_and_password_and_click_on_login_button(String userName, String password) {
        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input.btn-small")).submit();


    }

    @Then("Error message should appear")
    public void error_message_should_appear() {
       Assert.assertTrue(driver.findElement(By.cssSelector("input.btn.btn-small")).isDisplayed());
    }

    @After
    public void tearDown(){
        driver.close();
    }



}
