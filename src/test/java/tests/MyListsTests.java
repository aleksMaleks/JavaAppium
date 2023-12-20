package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUi;
import lib.ui.SavedListPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SavedListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "TestList";


    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);



        ArticlePageObject.waitForTitleElementWithSubstring("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySavedIOS();
        }

        ArticlePageObject.clickNavigationUpButton();
        ArticlePageObject.clickNavigationUpButton();

        NavigationUi NavigationUi = NavigationUiFactory.get(driver);
        NavigationUi.clickSavedList();

        SavedListPageObject SavedListPageObject = SavedListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            SavedListPageObject.clickSaveListByName(name_of_folder);
        }
        SavedListPageObject.swipeByArticleToDelete(article_title);
    }


    @Test
    public void testSaveTwoArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");

        //Save first article
        String name_first_article = "Java (programming language)";
        SearchPageObject.clickByArticleWithSubstring(name_first_article);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.clickNavigationUpButton();

        //Save second article
        String name_second_article = "Java";
        SearchPageObject.clickByArticleWithSubstring(name_second_article);
        ArticlePageObject.addSecondArticleToMyList();
        ArticlePageObject.clickNavigationUpButton();
        ArticlePageObject.clickNavigationUpButton();
        NavigationUi NavigationUi = NavigationUiFactory.get(driver);
        NavigationUi.clickSavedList();
        SavedListPageObject SavedListPageObject = SavedListPageObjectFactory.get(driver);
        SavedListPageObject.clickSaveListByName(name_of_folder);

        //Delete one article
        SavedListPageObject.swipeByArticleToDelete(name_first_article);
        SavedListPageObject.waitForArticleToDisappearByTitle(name_first_article);

        //Checking that there is a second article
        SavedListPageObject.clickByArticleWName(name_second_article);
        SavedListPageObject.waitForArticleToAppearByTitle(name_second_article);
    }
}
