package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedListPageObject extends MainPageObject {

    private static final String
            ARTICLE_NAME_BY_TPL = "//*[@class='android.view.ViewGroup']//*[@text='{ARTICLE_NAME}']";

    private static String getArticleNameByXpath(String name_of_folder) {
        return ARTICLE_NAME_BY_TPL.replace("{ARTICLE_NAME}", name_of_folder);
    }

    public SavedListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickSaveListByName(String name_of_folder) {
        String folder_name_xpath = getArticleNameByXpath(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find saved list '" + name_of_folder + "'",
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getArticleNameByXpath(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find saved article " + article_title, 15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getArticleNameByXpath(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article still present with title " + article_title, 15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getArticleNameByXpath(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find 'TestList' article in a save list"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void clickByArticleWName(String article_title) {
        String search_result_xpath = getArticleNameByXpath(article_title);
        this.waitForElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + article_title, 5);
    }
}
