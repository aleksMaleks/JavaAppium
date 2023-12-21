package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IOSMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_NAME_BY_TPL = "xpath:(//XCUIElementTypeStaticText[@value='{SUBSTRING}'])[1]";
        ARTICLE_TEXT_BY_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TEXT}')]";
        CLOSE_BUTTON_SYNC_POPUP_IOS = "id:Close";
    }

    public IOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
