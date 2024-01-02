package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "TestList";
    private static final String
            login = "Aleks",
            password = "08021977";


    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("ava (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElementWithSubstring("ava (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("ava (programming language)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySavedIOS();
        }

        if (Platform.getInstance().isMW()) {
            AuthtorizationPageObject Auth = new AuthtorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElementWithSubstring("Java");

            assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle("Java")
            );

            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.clickNavigationBackButton();
        SearchPageObject.clickCancelSearch();

        NavigationUi NavigationUi = NavigationUiFactory.get(driver);
        NavigationUi.openNavigation();
        NavigationUi.clickSavedList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.clickSaveListByName(name_of_folder);
        } else {
            MyListPageObject.clickCloseButtonSyncPopup();
        }
        MyListPageObject.swipeByArticleToDelete(article_title);
    }


    @Test
    public void testSaveTwoArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");

        //Save first article
        String name_first_article = "ava (programming language)";
        SearchPageObject.clickByArticleWithSubstring(name_first_article);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySavedIOS();
        }

        ArticlePageObject.clickNavigationBackButton();

        //Save second article
        String name_second_article = "Java";
        SearchPageObject.clickByArticleWithSubstring(name_second_article);
        ArticlePageObject.addSecondArticleToMyList();
        ArticlePageObject.clickNavigationBackButton();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.clickNavigationBackButton();
        } else {
            SearchPageObject.clickCancelSearch();
        }

        NavigationUi NavigationUi = NavigationUiFactory.get(driver);
        NavigationUi.clickSavedList();
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.clickSaveListByName(name_of_folder);
        } else {
            MyListPageObject.clickCloseButtonSyncPopup();
        }

        //Delete one article
        MyListPageObject.swipeByArticleToDelete(name_first_article);
        MyListPageObject.waitForArticleToDisappearByTitle(name_first_article);

        //Checking that there is a second article
        MyListPageObject.clickByArticleWName(name_second_article);
        String article_text = "his article is about the Indonesian island.";
        MyListPageObject.waitForArticleToAppearByText(article_text);
    }
}
