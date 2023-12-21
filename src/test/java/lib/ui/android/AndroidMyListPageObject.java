package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_NAME_BY_TPL = "xpath://*[@class='android.view.ViewGroup']//*[@text='{ARTICLE_NAME}']";
        ARTICLE_TEXT_BY_TPL= "xpath://XCUIElementTypeStaticText[contains(@name,'{TEXT}')]";
    }

    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
