package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
        features = "src/main/resources/features",
        glue = {"src/main/java/steps/LoginStepDefinitions.java"},
        tags = {"~@Ignore"}
        ,plugin = "json:target/cucumber-reports/CucumberTestReport.json")
public class TestRunner extends AbstractTestNGCucumberTests {

}
