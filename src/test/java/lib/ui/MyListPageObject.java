package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            ARTICLE_BY_TITLE_TPL,
            SAVE_LIST_NAME_BY_TPL,
            ARTICLE_TEXT_BY_TPL,
            CLOSE_BUTTON_SYNC_POPUP_IOS,
            REMOVE_FROM_SAVED_BUTTON;

    private static String getArticleNameByXpath(String name_of_folder) {
        return ARTICLE_BY_TITLE_TPL.replace("{SUBSTRING}", name_of_folder);
    }

    private static String getSaveListNameByXpath(String name_of_folder) {
        return SAVE_LIST_NAME_BY_TPL.replace("{SUBSTRING}", name_of_folder);
    }

    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    private static String getArticleTextByXpath(String article_text) {
        return ARTICLE_TEXT_BY_TPL.replace("{TEXT}", article_text);
    }

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickSaveListByName(String name_of_folder) {
        String folder_name_xpath = getSaveListNameByXpath(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find saved list: '" + name_of_folder + "'",
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

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }
}
