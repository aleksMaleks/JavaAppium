package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_FIELD = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_FIELD_TEXT = "xpath://*[@resource-id='org.wikipedia:id/search_container']" +
                "//*[@class='android.widget.TextView']";
        CANCEL_SEARCH_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']" +
                "//*[@class='android.view.ViewGroup']//*[contains(text(), '{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']" +
                "//*[@ class ='android.view.ViewGroup']//*[@text='{TITLE}']/../*[@text='{DESCRIPTION}']";
        SEARCH_RESUL_ROW = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    }


    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
