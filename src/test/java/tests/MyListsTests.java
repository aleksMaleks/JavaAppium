package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUi;
import lib.ui.SavedListPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElementWithSubstring("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");
        String name_of_folder = "TestList";
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.clickNavigationUpButton();
        ArticlePageObject.clickNavigationUpButton();

        NavigationUi NavigationUi = new NavigationUi(driver);
        NavigationUi.clickSavedList();

        SavedListPageObject SavedListPageObject = new SavedListPageObject(driver);
        SavedListPageObject.clickSaveListByName(name_of_folder);
        SavedListPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticleToMyList() {
        String name_of_folder = "TestList";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");

        //Save first article
        String name_first_article = "Java (programming language)";
        SearchPageObject.clickByArticleWithSubstring(name_first_article);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.clickNavigationUpButton();

        //Save second article
        String name_second_article = "Java";
        SearchPageObject.clickByArticleWithSubstring(name_second_article);
        ArticlePageObject.addSecondArticleToMyList();
        ArticlePageObject.clickNavigationUpButton();
        ArticlePageObject.clickNavigationUpButton();
        NavigationUi NavigationUi = new NavigationUi(driver);
        NavigationUi.clickSavedList();
        SavedListPageObject SavedListPageObject = new SavedListPageObject(driver);
        SavedListPageObject.clickSaveListByName(name_of_folder);

        //Delete one article
        SavedListPageObject.swipeByArticleToDelete(name_first_article);
        SavedListPageObject.waitForArticleToDisappearByTitle(name_first_article);

        //Checking that there is a second article
        SavedListPageObject.clickByArticleWName(name_second_article);
        SavedListPageObject.waitForArticleToAppearByTitle(name_second_article);
    }
}
