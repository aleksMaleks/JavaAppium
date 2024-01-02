package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class SearchTests extends CoreTestCase {

    @Test
    public void testVerifySearchResults() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("ava (programming language)");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("ava (programming language)");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonDisappear();
    }

    @Test
    public void testVerifySearchResult() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchIput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.verifyAllSearchResultsContainText(search_line);
    }

    @Test
    public void testVerifyTextSearchField() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.assertElementHasText("Search Wikipedia");
    }

    @Test
    public void testVerifySearchByThreeFirstResults() {
        String title1 = "Java", description1 = "sland in Indonesia",
                title2 = "JavaScript", description2 = "igh-level programming language",
                title3 = "Java (programming language)", description3 = "bject-oriented programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");

        SearchPageObject.waitForElementByTitleAndDescription(title1, description1);
        SearchPageObject.waitForElementByTitleAndDescription(title2, description2);
        SearchPageObject.waitForElementByTitleAndDescription(title3, description3);
    }
}
