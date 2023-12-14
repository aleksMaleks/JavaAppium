package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
            NAVIGATION_UP_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
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
//        if (Platform.getInstance().isAndroid()) {
//            return tittle_element.getAttribute("name");
//        }
        return tittle_element.getAttribute("name");
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
                    "Swipe error"
            );
        } else {
            this.swipeUpTitleElementAppear(
                    FOOTER_ELEMEN,
                    "Swipe error",
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

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,"Cannot find 'Add to list' button",5);

        this.waitForElementAndClick(
                SAVE_LIST_BUTTON,"Cannot find SaveList 'TestList' ",5);
    }

    public void clickNavigationUpButton() {
        this.waitForElementAndClick(
                NAVIGATION_UP_BUTTON,
                "Cannot find 'Navigate up' to cancel search", 5);
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
}
