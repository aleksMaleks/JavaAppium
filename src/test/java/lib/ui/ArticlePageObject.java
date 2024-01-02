package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            SEARCH_ARTICLE_TITLE_BY_SUBSTRING_TPL,
            ARTICLE_TITLE,
            ARTICLE_TITLE_DESCRIPTION,
            FOOTER_ELEMEN,
            SAVE_BUTTON,
            SAVE_LIST_BUTTON,
            ADD_TO_LIST_BUTTON,
            NAME_LIST_FIELD,
            OK_BUTTON,
            NAVIGATION_UP_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getXpathArticleTitle(String substring) {
        return SEARCH_ARTICLE_TITLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElementWithSubstring(String substring) {
        String article_title_xpath = getXpathArticleTitle(substring);
        return this.waitForElementPresent((article_title_xpath),
                "Cannot find article title", 5);
    }


    public String getArticleTitle(String substring) {
        WebElement tittle_element = waitForTitleElementWithSubstring(substring);
        if (Platform.getInstance().isMW()) {
            return tittle_element.getText();
        } else {
            return tittle_element.getAttribute("name");
        }
    }

    public String getArticleDescriptionByIdInArticle() {
        return this.waitForElementAndGetAttribute(
                ARTICLE_TITLE_DESCRIPTION,
                "contentDescription",
                "Cannot find title of article ",
                15
        );
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMEN,
                    "Cannot find the end of article"
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTitleElementAppear(
                    FOOTER_ELEMEN,
                    "Cannot find the end of article",
                    40
            );
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMEN,
                    "Cannot find the end of article",
                    40
            );
        }
    }


    public void addFirstArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON, "Cannot find 'Save' button", 5);

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON, "Cannot find 'Add to list' button", 5);

        this.waitForElementAndSendKeys(NAME_LIST_FIELD, name_of_folder,
                "Cannot find 'Name list' field", 5);

        this.waitForElementAndClick(OK_BUTTON, "Cannot find 'OK' button", 5
        );
    }

    public void addSecondArticleToMyList() {
        this.waitForElementAndClick(
                SAVE_BUTTON,"Cannot find 'Save' button",5);

        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    ADD_TO_LIST_BUTTON,"Cannot find 'Add to list' button",5);

            this.waitForElementAndClick(
                    SAVE_LIST_BUTTON,"Cannot find SaveList 'TestList' ",5);
        }
    }

    public void clickNavigationBackButton() {
        this.waitForElementAndClick(
                NAVIGATION_UP_BUTTON,
                "Cannot find 'Navigate up' to cancel search", 3);
    }

    public void clickSaveButton() {
        this.waitForElementAndClick(
                NAVIGATION_UP_BUTTON,
                "Cannot find 'Navigate up' to cancel search", 5);
    }

    public void assertArticleTitlePresent() {
        By by = this.getLocatorByString(ARTICLE_TITLE);
        WebElement element = driver.findElement(by);
        String actual_text = element.getAttribute("contentDescription");
        System.out.println(actual_text);
    }

    public void addArticlesToMySavedIOS() {
        this.waitForElementAndClick(SAVE_BUTTON,
                "Cannot find options to add article to reading list",
                5
        );
    }

    public void addArticleToMySaved() {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(ADD_TO_LIST_BUTTON,
                "Cannot find options to add article to saved list", 5);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    ADD_TO_LIST_BUTTON,
                    "Cannot find button to add article to saved list after removing it from the list before"
            );
        }
    }

    public void closeArticle() {
        if (Platform.getInstance().isIOS() || (Platform.getInstance().isAndroid())) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5
            );
        } else {
            System.out.println("Method closeArticle() does nothing for platform "
                    + Platform.getInstance().getPlatformVar());
        }
    }
}
