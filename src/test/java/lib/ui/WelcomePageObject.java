package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
            SKIP_BUTTON_IOS = "xpath://XCUIElementTypeButton[@name='Skip']",
            SKIP_BUTTON_ANDROID = "id:org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_FIELD_TEXT = "id:Search Wikipedia";

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickScipIOS() {
        this.waitForElementAndClick(SKIP_BUTTON_IOS,
                "Cannot find and click Skip button", 5);
    }

    public void clickScipAndroid() {
        this.waitForElementAndClick(SKIP_BUTTON_ANDROID,
                "Cannot find and click Skip button", 5);
    }

    public void assertElementHasText(String expected_text) {
        WebElement web_element = waitForElementPresent(
                SEARCH_FIELD_TEXT,
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String actual_text = web_element.getAttribute("name");

        Assert.assertEquals(
                "Cannot find 'Search Wikipedia' input",
                expected_text,
                actual_text
        );
    }
}
