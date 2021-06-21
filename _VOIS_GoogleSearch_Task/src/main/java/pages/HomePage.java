
/**
 * pages contain the classes that represent the different multiple webpages
 */
package pages;

import actions.BrowserActions;
import actions.UI_Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.files.PropertiesFileManager;

/**
 * HomePage class home page of the website
 *
 * @author Kareem Mohamed
 * @version 1.0
 * @since 18/6/2021
 */
public class HomePage {
    private WebDriver driver;
    private UI_Actions uiActions;
    private BrowserActions browserActions;

    /**
     * object to read data from properties files
     */
    private PropertiesFileManager proFilMgr;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        uiActions = new UI_Actions(driver);
        browserActions = new BrowserActions(driver);

        /* specify the properties file sources */
        proFilMgr = new PropertiesFileManager("src/main/resources/Configurations/homePage.properties");

    }

    /**
     * clearing the a text field
     *
     * @throws Exception if the webelement itself does not exist , or can't be located
     *                   it throws new exception 'This element can't be found!'
     */
    public void clearSearchTextField() throws Exception {
        uiActions.locateElement(proFilMgr.getPropertyValue("SEARCH_FIELD_LOCATOR"), UI_Actions.Locators.NAME).clear();
    }

    /**
     * Search for something using google engines by specifying the text to search for
     *
     * @param searchText text to be written and searched for it
     * @throws Exception if the webelement itself does not exist , or can't be located
     *                   it throws new exception 'This element can't be found!'
     */
    public void searchFor(String searchText) throws Exception {

        uiActions.locateElement(proFilMgr.getPropertyValue("SEARCH_FIELD_LOCATOR"), UI_Actions.Locators.NAME).sendKeys(searchText, Keys.ENTER);

    }

    /**
     * Navigate to home page of the website
     */
    public void goToHome() {
        uiActions.navigateToPage(proFilMgr.getPropertyValue("HOME_URL"));
    }

    /**
     * Navigate to Google Apps window
     *
     * @throws Exception if the webelement itself does not exist , or can't be located
     *                   it throws new exception 'This element can't be found!'
     */
    public void goToGoogleApps() throws Exception {

        uiActions.clickOn(proFilMgr.getPropertyValue("GOOGLE_APP_LOCATOR"), UI_Actions.Locators.XPATH);
    }

    /**
     * Click on google search button to search for something
     *
     * @throws Exception if the google search button itself does not exist , or can't be located
     *                   it throws new exception 'This element can't be found!'
     */
    public void clickOnGoogleSearch() throws Exception {
        uiActions.clickOn(proFilMgr.getPropertyValue("GOOGLE_SEARCH_BUTTON"), UI_Actions.Locators.XPATH);
    }

    /**
     * Navigate to the next search result page by clicking on next hyper link
     *
     * @throws Exception if the next hyper link itself does not exist , or can't be located or
     *                   is not clickable, it throws new exception 'This element can't be found!'
     */
    public void goToNextPage() throws Exception {
        uiActions.scrollToEnd();
        uiActions.clickOn(proFilMgr.getPropertyValue("NEXT_PAGE_LOCATOR"), UI_Actions.Locators.ID);

    }

}
