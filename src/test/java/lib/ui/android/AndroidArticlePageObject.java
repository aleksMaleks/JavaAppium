package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        SEARCH_ARTICLE_TITLE_BY_SUBSTRING_TPL = "xpath://android.view.View[@content-desc='{SUBSTRING}']";
        ARTICLE_TITLE = "xpath://*[@resource-id='pcs']/android.view.View[1]/android.view.View[1]";
        ARTICLE_TITLE_DESCRIPTION = "id:pcs-edit-section-title-description";
        FOOTER_ELEMEN = "xpath://*[@content-desc='View article in browser']";
        SAVE_BUTTON = "id:org.wikipedia:id/page_save";
        SAVE_LIST_BUTTON = "id:org.wikipedia:id/item_title";
        ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
        NAME_LIST_FIELD = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "id:android:id/button1";
        NAVIGATION_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
