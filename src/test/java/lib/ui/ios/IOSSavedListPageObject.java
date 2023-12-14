package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SavedListPageObject;

public class IOSSavedListPageObject extends SavedListPageObject {

    static {
        ARTICLE_NAME_BY_TPL = "xpath://XCUIElementTypeLink[contains(@name='{ARTICLE_NAME}')]";
    }

    public IOSSavedListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
