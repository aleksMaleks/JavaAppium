package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        SEARCH_ARTICLE_TITLE_BY_SUBSTRING_TPL = "xpath:(//XCUIElementTypeStaticText[@value='{SUBSTRING}'])[1]";
        ARTICLE_TITLE = "id:Java (programming language)";
        ARTICLE_TITLE_DESCRIPTION = "id:pcs-edit-section-title-description";
        FOOTER_ELEMEN = "id:View article in browser";
        SAVE_BUTTON = "id:Save for later";
        SAVE_LIST_BUTTON = "id:org.wikipedia:id/item_title";
        ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
        NAME_LIST_FIELD = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "id:android:id/button1";
        NAVIGATION_UP_BUTTON = "id:Search";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
