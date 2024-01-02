package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUi extends MainPageObject {

    protected static String
            SAVED_LIST_BUTTON,
            OPEN_NAVIGATION;
    public NavigationUi(RemoteWebDriver driver) {
        super(driver);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(
                    OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    5
            );
        } else {
            System.out.println("Method openNavigation() does nothing for platform "
                    + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickSavedList() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    SAVED_LIST_BUTTON,
                    "Cannot find 'Saved' button",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    SAVED_LIST_BUTTON,
                    "Cannot find 'Saved' button",
                    3
            );
        }
    }
}
