/**
 *  pages contain the classes that represent the different multiple webpages
 */
package pages;

import actions.BrowserActions;
import actions.UI_Actions;
import org.openqa.selenium.WebDriver;
import utils.files.PropertiesFileManager;

/**
 * SearchResultPage it represents the results page after searching
 * for something in using google search engines
 *
 *  @author Kareem Mohamed
 *  @version 1.0
 *  @since 18/6/2021
 */
public class SearchResultPage {
    private WebDriver driver ;
    private UI_Actions uiActions;
    private BrowserActions browserActions  ;
    private PropertiesFileManager proFilMgr ;

    /**
     * @param driver: Initialize SearchResultPage webpage with Webdriver instance (driver)
     */
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        uiActions = new UI_Actions(driver) ;
        browserActions = new BrowserActions(driver) ;
        proFilMgr = new PropertiesFileManager("src/main/resources/Configurations/searchResultsPage.properties");
    }

    /**
     *
     * @param searchResultNumber: specify the number of the search result to click on it
     * @throws Exception: if the link is not clickable or the webelement itself does not exist
     *  it throws new exception 'This element can't be found!'
     */
    public void clickOnSearchResult(int searchResultNumber) throws Exception {
        uiActions.clickOn(String.format(proFilMgr.getPropertyValue("SEARCH_RESULT_NUMBER"),searchResultNumber), UI_Actions.Locators.XPATH);
    }

    /**
     *
     * @return the number of the search results appeared on the page
     * @throws Exception if  webelement itself does not exist
     * it throws new exception 'These elements can't be found!'
     */
    public int getSearchResultCount() throws Exception {
        return  uiActions.locateElements(proFilMgr.getPropertyValue("SEARCH_RESULT_CLASS"), UI_Actions.Locators.XPATH).size() ;
    }
}
