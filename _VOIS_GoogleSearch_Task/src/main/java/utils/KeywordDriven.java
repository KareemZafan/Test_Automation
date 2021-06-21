package utils;

import actions.BrowserActions;
import actions.UI_Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


/**
 * This class contain the action need to cover a specific scenario which will
 * be externally given via data file such as excel sheets
 *
 *  @author Kareem Mohamed
 *  @version 1.0
 *  @since 18/6/2021
 */
public class KeywordDriven {
    private WebDriver driver;
    private BrowserActions browserActions;
    private UI_Actions uiActions;

    public KeywordDriven(WebDriver driver) {

        this.driver = driver;
        browserActions = new BrowserActions(driver);
        uiActions = new UI_Actions(driver);

    }

    public void getUrl(String url) {
        uiActions.navigateToPage(url);
    }

    public void enterSearchKeywords(String selector, String locator, String searchKeywords) throws Exception {
        switch (locator) {
            case "id":
                driver.findElement(By.id(selector)).sendKeys(searchKeywords, Keys.ENTER);
                break;
            case "xpath":
                driver.findElement(By.xpath(selector)).sendKeys(searchKeywords, Keys.ENTER);
                break;
            case "linkText":
                driver.findElement(By.linkText(selector)).sendKeys(searchKeywords, Keys.ENTER);
                break;
            case "partialLinkText":
                driver.findElement(By.partialLinkText(selector)).sendKeys(searchKeywords, Keys.ENTER);
                break;
            case "cssSelector":
                driver.findElement(By.cssSelector(selector)).sendKeys(searchKeywords, Keys.ENTER);
                break;
            case "name":
                driver.findElement(By.name(selector)).sendKeys(searchKeywords, Keys.ENTER);
                break;
            case "tagName":
                driver.findElement(By.tagName(selector)).sendKeys(searchKeywords, Keys.ENTER);
                break;
            case "className":
                driver.findElement(By.className(selector)).sendKeys(searchKeywords, Keys.ENTER);
                break;
            default:
                throw new Exception("Invalid element objects identifier ");

        }

    }

    public void scrollToEnd() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void gotoNextPage(String selector, String locator) throws Exception {
        switch (locator) {
            case "id":
                driver.findElement(By.id(selector)).click();
                break;
            case "xpath":
                driver.findElement(By.xpath(selector)).click();
                break;
            case "linkText":
                driver.findElement(By.linkText(selector)).click();
                break;
            case "partialLinkText":
                driver.findElement(By.partialLinkText(selector)).click();
                break;
            case "cssSelector":
                driver.findElement(By.cssSelector(selector)).click();
                break;
            case "name":
                driver.findElement(By.name(selector)).click();
                break;
            case "tagName":
                driver.findElement(By.tagName(selector)).click();
                break;
            case "className":
                driver.findElement(By.className(selector)).click();
                break;
            default:
                throw new Exception("Invalid element objects identifier ");

        }
    }

    public int countSearchResults(String selector, String locator) throws Exception {
        switch (locator) {
            case "id":
                return driver.findElements(By.id(selector)).size();
            case "xpath":
                return driver.findElements(By.xpath(selector)).size();
            case "linkText":
                return driver.findElements(By.linkText(selector)).size();
            case "partialLinkText":
                return driver.findElements(By.partialLinkText(selector)).size();
            case "cssSelector":
                return driver.findElements(By.cssSelector(selector)).size();

            case "name":
                return driver.findElements(By.name(selector)).size();
            case "tagName":
                return driver.findElements(By.tagName(selector)).size();
            case "className":
                return driver.findElements(By.className(selector)).size();
            default:
                throw new Exception("Invalid element objects identifier !");

        }
    }

    public void closeDriver() {
        browserActions.closeAllDrivers();
    }
}
