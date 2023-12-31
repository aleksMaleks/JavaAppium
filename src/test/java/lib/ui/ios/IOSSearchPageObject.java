package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_FIELD = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_FIELD_TEXT = "id:Search Wikipedia";
        CANCEL_SEARCH_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']/" +
                "../XCUIElementTypeStaticText[@name='{DESCRIPTION}']";
        SEARCH_RESUL_ROW = "xpath://XCUIElementTypeCell/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
