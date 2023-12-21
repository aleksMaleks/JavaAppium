package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
//        ARTICLE_NAME_BY_TPL = "xpath://*[@class='android.view.ViewGroup']//*[@text='{ARTICLE_NAME}']";
        ARTICLE_NAME_BY_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";
        SAVE_LIST_NAME_BY_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']";
        ARTICLE_TEXT_BY_TPL= "xpath://android.view.View[contains(@text,'{TEXT}')]";
    }

    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
