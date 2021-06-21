package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class will re-produce the failed testcases at maximum three times
 *
 *  @author Kareem Mohamed
 *  @version 1.0
 *  @since 18/6/2021
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private int counter = 0;
    int retryLimit = 3; /* if the test failed it will be reproduced at maximum 3 times */

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (counter < retryLimit) {
            counter++;
            return true;
        }
        return false;
    }
}

