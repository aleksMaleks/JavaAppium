package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testVerifySearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonDisappear();
    }

    @Test
    public void testVerifySearchResult() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchIput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.verifyAllSearchResultsContainText(search_line);
    }

    @Test
    public void testVerifyTextSearchField() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.assertElementHasText("Search Wikipedia");
    }

    @Test
    public void testVerifySearchByThreeFirstResults() {
        String title1 = "Java", description1 = "Island in Indonesia",
                title2 = "JavaScript", description2 = "High-level programming language",
                title3 = "Java (programming language)", description3 = "Object-oriented programming language";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");

        SearchPageObject.waitForElementByTitleAndDescription(title1, description1);
        SearchPageObject.waitForElementByTitleAndDescription(title2, description2);
        SearchPageObject.waitForElementByTitleAndDescription(title3, description3);
    }
}
