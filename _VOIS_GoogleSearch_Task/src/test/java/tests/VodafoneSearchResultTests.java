package tests;

import base_tests.BaseTests;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchResultPage;
import utils.RetryAnalyzer;
import utils.files.PropertiesFileManager;

/**
 * This class contains the tests related to search results performed on and engine such
 * google yahoo according to base url of the engine
 *
 * @author Kareem Mohamed
 * @version 1.0
 * @since 18/6/2021
 */
public class VodafoneSearchResultTests extends BaseTests {
    private PropertiesFileManager proFilMgr;

    /**
     * Instantiate PropertiesFileManager with the path of the properties file which
     * contains the object identifiers
     */
    public VodafoneSearchResultTests() {
        proFilMgr = new PropertiesFileManager("src/main/resources/Configurations/vodafoneSearchResultTests.properties");
    }

    /**
     * Test the number of search results appeared on the page 2 are equal to
     * their corresponding on page 3 or not and test scenario specified below
     * <p>
     * Test Scenario:
     * goto google search and type Vodafone
     * then scroll down and goto the next page
     * get the number of the search results on page 2
     * then scroll down and goto the next page
     * get the number of the search results on page 3
     * test the number of the search of the both pages 2,3 are equal or not
     */
    @Test(retryAnalyzer =RetryAnalyzer.class)
    public void testSearchResultsOnP2_P3() {
        try {
            homePage.clearSearchTextField();
            homePage.searchFor(proFilMgr.getPropertyValue("SEARCH_KEYWORDS"));

            /*Page 2 Search Result Counts */
            uiActions.scrollToEnd();
            homePage.goToNextPage();

            SearchResultPage searchResultPage = new SearchResultPage(driver);
            int page2ResCounts = searchResultPage.getSearchResultCount();
            System.out.println(page2ResCounts);

            /*Page 3 Search Result Counts */
            uiActions.scrollToEnd();
            homePage.goToNextPage();
            int page3ResCounts = searchResultPage.getSearchResultCount();
            System.out.println(page3ResCounts);

            /* test the both results */
            Assert.assertEquals(page2ResCounts, page3ResCounts);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
