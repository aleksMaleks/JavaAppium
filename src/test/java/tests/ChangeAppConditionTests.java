package tests;

import io.appium.java_client.AppiumDriver;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testRotate() {
        this.rotateScreenPortrait();
    }

    @Test
    public void testChangeScreenOrientationOnSearchResult() {

        if (Platform.getInstance().isMW()) {
            return;
        }

        AppiumDriver driver = (AppiumDriver) this.driver;
        if (driver.getOrientation().equals(LANDSCAPE)) {
            this.rotateScreenPortrait();
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleDescriptionByIdInArticle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleDescriptionByIdInArticle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleDescriptionByIdInArticle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }


    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
        this.backgroundApp(Duration.ofSeconds(2));
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }
}
