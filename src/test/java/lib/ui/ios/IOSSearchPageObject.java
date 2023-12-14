package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_FIELD = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_FIELD_TEXT = "id:Search Wikipedia";
        CANCEL_SEARCH_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']/" +
                "../XCUIElementTypeStaticText[@name='{DESCRIPTION}']";
        SEARCH_RESUL_ROW = "xpath://XCUIElementTypeStaticText";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
