package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            SEARCH_ARTICLE_TITLE_BY_SUBSTRING_TPL = "//android.view.View[@content-desc='{SUBSTRING}']",
            ARTICLE_TITLE = "//*[@resource-id='pcs']/android.view.View[1]/android.view.View[1]",
            ARTICLE_TITLE_DESCRIPTION = "pcs-edit-section-title-description",
            FOOTER_ELEMEN = "//*[@content-desc='View article in browser']",
            SAVE_BUTTON = "org.wikipedia:id/page_save",
            SAVE_LIST_BUTTON = "org.wikipedia:id/item_title",
            ADD_TO_LIST_BUTTON = "org.wikipedia:id/snackbar_action",
            NAME_LIST_FIELD = "org.wikipedia:id/text_input",
            OK_BUTTON = "android:id/button1",
            NAVIGATION_UP_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

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
        return this.waitForElementPresent(By.xpath(article_title_xpath),
                "Cannot find article title", 5);
    }


    public String getArticleTitle(String substring) {
        WebElement tittle_element = waitForTitleElementWithSubstring(substring);
        return tittle_element.getAttribute("name");
    }

    public String getArticleDescriptionByIdInArticle() {
        return this.waitForElementAndGetAttribute(
                By.id(ARTICLE_TITLE_DESCRIPTION),
                "contentDescription",
                "Cannot find title of article ",
                15
        );
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMEN), "Swipe error");
    }


    public void addFirstArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON), "Cannot find 'Save' button", 5);

        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON), "Cannot find 'Add to list' button", 5);

        this.waitForElementAndSendKeys(By.id(NAME_LIST_FIELD), name_of_folder,
                "Cannot find 'Name list' field", 5);

        this.waitForElementAndClick(By.id(OK_BUTTON), "Cannot find 'OK' button", 5
        );
    }

    public void addSecondArticleToMyList() {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),"Cannot find 'Save' button",5);

        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON),"Cannot find 'Add to list' button",5);

        this.waitForElementAndClick(
                By.id(SAVE_LIST_BUTTON),"Cannot find SaveList 'TestList' ",5);
    }

    public void clickNavigationUpButton() {
        this.waitForElementAndClick(
                By.xpath(NAVIGATION_UP_BUTTON),
                "Cannot find 'Navigate up' to cancel search", 5);
    }

    public void clickSaveButton() {
        this.waitForElementAndClick(
                By.xpath(NAVIGATION_UP_BUTTON),
                "Cannot find 'Navigate up' to cancel search", 5);
    }

    public void assertArticleTitlePresent() {
//        this.waitForElementPresent(
//                By.xpath("//*[@resource-id='pcs']/android.view.View[1]/android.view.View[1]"),
//                "Cannot find element");

        WebElement element = driver.findElement(By.xpath(ARTICLE_TITLE));
        String actual_text = element.getAttribute("contentDescription");
        System.out.println(actual_text);
    }
}
