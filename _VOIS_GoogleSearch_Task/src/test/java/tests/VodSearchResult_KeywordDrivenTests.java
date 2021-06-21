package tests;

import base_tests.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RetryAnalyzer;
import utils.files.ddt.ExcelFileManager;

/**
 * This class contains the same testcase but with driving the object
 * identifiers from external excel file
 *
 * @author Kareem Mohamed
 * @version 1.0
 * @since 18/6/2021
 */
public class VodSearchResult_KeywordDrivenTests extends BaseTests {
    private ExcelFileManager excelFileManager;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testSearchCountsOnP2_P3() {
        try {

            excelFileManager = new ExcelFileManager("src/main/resources/test_data/Keyword Driven.xlsx", "Sheet1");
            keywordDriven.getUrl(excelFileManager.getCellData(2, 4));
            keywordDriven.enterSearchKeywords(excelFileManager.getCellData(3, 2), excelFileManager.getCellData(3, 1), excelFileManager.getCellData(3, 4));
            keywordDriven.scrollToEnd();
            keywordDriven.gotoNextPage(excelFileManager.getCellData(5, 2), excelFileManager.getCellData(5, 1));
            int page2Results = keywordDriven.countSearchResults(excelFileManager.getCellData(6, 2), excelFileManager.getCellData(6, 1));
            System.out.println("Search results count on page2 = "+page2Results);
            keywordDriven.gotoNextPage(excelFileManager.getCellData(5, 2), excelFileManager.getCellData(5, 1));
            int page3Results = keywordDriven.countSearchResults(excelFileManager.getCellData(6, 2), excelFileManager.getCellData(6, 1));
            System.out.println("Search results count on page3 = "+page3Results);
            Assert.assertEquals(page2Results, page3Results);
            keywordDriven.closeDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
