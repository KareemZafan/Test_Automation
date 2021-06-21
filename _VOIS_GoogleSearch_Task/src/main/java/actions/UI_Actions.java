/**
 * actions contains the different actions can be done through graphical user interface
 * such a clicking on elements of typing in text fields,...
 * layer or through the browser side such a window navigation forward, backward ,...
 */
package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * UI_Actions: contains the main functionalities you can do or perform on a webpage such
 * clicking on a link , scrolling down or up, dealing with web elements buttons , text fields, text areas , ...
 *
 * @author Kareem Mohamed
 * @version 1.0
 * @since 18/6/2021
 */
public class UI_Actions {
    private WebDriver driver;
    private WebDriverWait wait;

    public UI_Actions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 100);

    }

    /**
     * Locate a web element by specifying its locator type and selector
     *
     * @param selector the string which specify the webelement on the DOM
     * @param locator type of locator such a xpath , cssSelector , ID, ...
     * @return located webelement which you deal with such a clicking on, clearing , ...
     * @throws Exception if the webelement itself does not exist , or can't be located
     * it throws new exception 'This element can't be found!'
     */
    public WebElement locateElement(String selector, Locators locator) throws Exception {
        switch (locator) {
            case ID -> {
                return driver.findElement(By.id(selector));

            }

            case TAG_NAME -> {
                return driver.findElement(By.tagName(selector));

            }

            case CSS_SElECTOR -> {
                return driver.findElement(By.cssSelector(selector));
            }

            case NAME -> {
                return driver.findElement(By.name(selector));

            }

            case LINK_TEXT -> {
                return driver.findElement(By.linkText(selector));
            }

            case PARTIAL_LINK_TEXT -> {
                return driver.findElement(By.partialLinkText(selector));
            }

            case XPATH -> {
                return driver.findElement(By.xpath(selector));
            }

            default -> throw new Exception("This element can't be found!");
        }
    }

    /**
     * locate list of webelements by specifying their locator and selector
     *
     * @param selector the string which specify the webelement on the DOM
     * @param locator type of locator such a xpath , cssSelector , ID, ...
     * @return list of located webelements which you can deal with such a clicking on, clearing , ...
     * @throws Exception if the webelements themselves do not exist , or can't be located
     * it throws new exception 'These elements can't be found!'
     */
    public List<WebElement> locateElements(String selector, Locators locator) throws Exception {
        switch (locator) {
            case ID -> {
                return driver.findElements(By.id(selector));
            }

            case TAG_NAME -> {
                return driver.findElements(By.tagName(selector));

            }

            case CSS_SElECTOR -> {
                return driver.findElements(By.cssSelector(selector));
            }

            case NAME -> {
                return driver.findElements(By.name(selector));

            }

            case LINK_TEXT -> {
                return driver.findElements(By.linkText(selector));
            }

            case PARTIAL_LINK_TEXT -> {
                return driver.findElements(By.partialLinkText(selector));
            }

            case XPATH -> {
                return driver.findElements(By.xpath(selector));
            }

            default -> throw new Exception("These elements can't be found!");
        }
    }

    /**
     * click on a webelement by specifying its selector and locator
     *
     * @param selector the string which specify the webelement on the DOM
     * @param locator  type of locator such a xpath , cssSelector , ID, ...
     * @throws Exception if web element is not clickable or  the webelement itself do not exist , or can't be located
     * it throws new exception 'This element can't be found!'
     */
    public void clickOn(String selector, Locators locator) throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(locateElement(selector, locator))).click();
    }

    /**
     * get the text appeared on a webelement
     * @param selector the string which specify the webelement on the DOM
     * @param locator  type of locator such a xpath , cssSelector , ID, ...
     * @return the specified webelement text
     * @throws Exception if the webelement itself do not exist , or can't be located
     * it throws new exception 'This element can't be found!'
     */
    public String getText(String selector, Locators locator) throws Exception {
        return wait.until(ExpectedConditions.
                visibilityOf((locateElement(selector, locator)))).
                getText();
    }

    /**
     * write down on a webelement
     *
     * @param selector the string which specify the webelement on the DOM
     * @param locator  type of locator such a xpath , cssSelector , ID, ...
     * @param text specify the text to fill the webelement with
     * @throws Exception if the webelement itself do not exist , or can't be located
     * it throws new exception 'This element can't be found!'
     */
    public void setText(String selector, Locators locator, String text) throws Exception {
        wait.until(ExpectedConditions.
                visibilityOf((locateElement(selector, locator)))).sendKeys(text);
    }

    /**
     * Locator types -->xpath , id , name ...
     */
    public enum Locators {
        XPATH,
        LINK_TEXT,
        PARTIAL_LINK_TEXT,
        TAG_NAME,
        CSS_SElECTOR,
        ID,
        NAME
    }

    /**
     * navigate to a specific page and waiting for the presence of DOM to be ready
     *
     * @param url specify the page url to navigate to
     */
    public void navigateToPage(String url) {
        driver.navigate().to(url);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).
                executeScript("return document.readyState").equals("complete"));

    }

    /**
     * scroll down by a value
     *
     * @param scrollHeight specify the height to scroll down
     */
    public void scrollBy(int scrollHeight) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("window.scrollBy(0,%d)", scrollHeight));
    }

    /**
     * scroll down till end of DOM
     */
    public void scrollToEnd() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}
