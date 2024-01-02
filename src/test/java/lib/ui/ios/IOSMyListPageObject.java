package lib.ui.ios;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath:(//XCUIElementTypeStaticText[@value='{SUBSTRING}'])[1]";
        ARTICLE_TEXT_BY_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TEXT}')]";
        CLOSE_BUTTON_SYNC_POPUP_IOS = "id:Close";
    }

    public IOSMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
