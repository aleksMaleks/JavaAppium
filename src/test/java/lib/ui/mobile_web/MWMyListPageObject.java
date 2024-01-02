package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}']";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}']" +
                "/../../div[contains(@class,'watched')]";
        ARTICLE_TEXT_BY_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TEXT}')]";
        CLOSE_BUTTON_SYNC_POPUP_IOS = "id:Close";
    }

    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}