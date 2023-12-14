package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SavedListPageObject;

public class AndroidSavedListPageObject extends SavedListPageObject {

    static {
        ARTICLE_NAME_BY_TPL = "xpath://*[@class='android.view.ViewGroup']//*[@text='{ARTICLE_NAME}']";
    }

    public AndroidSavedListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
