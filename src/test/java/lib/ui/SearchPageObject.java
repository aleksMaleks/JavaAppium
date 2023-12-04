package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_FIELD = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_FIELD_TEXT = "//*[@resource-id='org.wikipedia:id/search_container']" +
                    "//*[@class='android.widget.TextView']",
            CANCEL_SEARCH_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']" +
                    "//*[@class='android.view.ViewGroup']//*[@text='{SUBSTRING}']",
            SEARCH_RESUL_ROW = "//*[@resource-id='org.wikipedia:id/search_results_list']" +
                    "//*[@resource-id='org.wikipedia:id/page_list_item_title']";

    //*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']//*[@text='{SUBSTRING}']

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchIput() {
        this.waitForElementAndClick(By.xpath(SEARCH_FIELD),
                "Cannot find and click search element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_FIELD),
                "Cannot find search input after clicking init element");
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.xpath(CANCEL_SEARCH_BUTTON),
                "Cannot find 'Navigate up' to cancel search", 5);
    }

    public void waitForCancelButtonDisappear() {
        this.waitForElementNotPresent(By.xpath(CANCEL_SEARCH_BUTTON),
                "Search article is still present on the page", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.xpath(CANCEL_SEARCH_BUTTON),
                "Cannot find and click search cancel (Navigate up) button", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_FIELD), search_line,
                "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring, 5);
    }

    public void verifyAllSearchResultsContainText(String contains_text) {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESUL_ROW),
                "Cannot find search result row with text '" + contains_text + "'"
        );

        List<WebElement> search_results = driver.findElements(
                By.xpath(SEARCH_RESUL_ROW));
        for (WebElement search_result : search_results) {
            assertTrue(
                    "Search result doesn't contain text '" + contains_text + "'",
                    search_result.getText().contains(contains_text)
            );
        }
    }


    public void assertElementHasText(String expected_text) {
        WebElement web_element = waitForElementPresent(
                By.xpath(SEARCH_FIELD_TEXT),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String actual_text = web_element.getAttribute("text");

        Assert.assertEquals(
                "Cannot find 'Search Wikipedia' input",
                expected_text,
                actual_text
        );
    }
}
