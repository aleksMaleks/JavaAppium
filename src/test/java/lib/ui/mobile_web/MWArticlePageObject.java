package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        SEARCH_ARTICLE_TITLE_BY_SUBSTRING_TPL = "xpath:(//XCUIElementTypeStaticText[@value='{SUBSTRING}'])[1]";
        ARTICLE_TITLE = "css:#content h1";
        ARTICLE_TITLE_DESCRIPTION = "id:pcs-edit-section-title-description";
        FOOTER_ELEMEN = "css:footer";
        SAVE_BUTTON = "id:Save for later";
        SAVE_LIST_BUTTON = "id:org.wikipedia:id/item_title";
        ADD_TO_LIST_BUTTON = "css:#page-actions li#page-actions-watch";
        NAME_LIST_FIELD = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "id:android:id/button1";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watch button";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";
//        NAVIGATION_UP_BUTTON = "id:Search";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}