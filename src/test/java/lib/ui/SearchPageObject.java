package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_FIELD,
            SEARCH_FIELD_TEXT,
            CANCEL_SEARCH_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION,
            SEARCH_RESUL_ROW;


    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description) {
        String str = SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION.replace("{TITLE}", title);
        return str.replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void initSearchIput() {
        this.waitForElementAndClick(SEARCH_FIELD,
                "Cannot find and click search element", 5);
        this.waitForElementPresent(SEARCH_FIELD,
                "Cannot find search input after clicking init element");
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(CANCEL_SEARCH_BUTTON,
                "Cannot find 'Navigate up' to cancel search", 5);
    }

    public void waitForCancelButtonDisappear() {
        this.waitForElementNotPresent(CANCEL_SEARCH_BUTTON,
                "Search article is still present on the page", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(CANCEL_SEARCH_BUTTON,
                "Cannot find and click search cancel (Navigate up) button", 3);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_FIELD, search_line,
                "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,
                "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,
                "Cannot find and click search result with substring " + substring, 5);
    }

    public void verifyAllSearchResultsContainText(String contains_text) {
        this.waitForElementPresent(
                SEARCH_RESUL_ROW,
                "Cannot find search result row with text '" + contains_text + "'"
        );
        By by = this.getLocatorByString(SEARCH_RESUL_ROW);
        List<WebElement> search_results = driver.findElements(by);
        for (WebElement search_result : search_results) {
            assertTrue(
                    "Search result doesn't contain text '" + contains_text + "'",
                    search_result.getText().contains(contains_text)
            );
        }
    }

    public void assertElementHasText(String expected_text) {
        WebElement web_element = waitForElementPresent(
                SEARCH_FIELD_TEXT,
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String actual_text = web_element.getAttribute("name");

        Assert.assertEquals(
                "Cannot find 'Search Wikipedia' input",
                expected_text,
                actual_text
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_xpath,
                "Cannot find search result with Title '" + title +
                        "' and Description '" + description + "'");
    }
}
