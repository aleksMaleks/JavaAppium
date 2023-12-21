package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            ARTICLE_NAME_BY_TPL,
            ARTICLE_TEXT_BY_TPL,
            CLOSE_BUTTON_SYNC_POPUP_IOS;

    private static String getArticleNameByXpath(String name_of_folder) {
        return ARTICLE_NAME_BY_TPL.replace("{SUBSTRING}", name_of_folder);
    }

    private static String getArticleTextByXpath(String article_text) {
        return ARTICLE_TEXT_BY_TPL.replace("{TEXT}", article_text);
    }

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickSaveListByName(String name_of_folder) {
        String folder_name_xpath = getArticleNameByXpath(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find saved list '" + name_of_folder + "'",
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getArticleNameByXpath(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title: " + article_title, 15
        );
    }

    public void waitForArticleToAppearByText(String article_text) {
        String article_xpath = getArticleTextByXpath(article_text);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by text: " + article_text, 15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getArticleNameByXpath(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title, 15
        );
    }



    public void clickByArticleWName(String article_title) {
        String search_result_xpath = getArticleNameByXpath(article_title);
        this.waitForElementAndClick(search_result_xpath,
                "Cannot find and click search result with substring " + article_title, 5);
    }

    public void clickCloseButtonSyncPopup() {
        this.waitForElementAndClick(
                CLOSE_BUTTON_SYNC_POPUP_IOS,
                "Cannot find close button sync Popup iOs", 15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getArticleNameByXpath(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }
}
