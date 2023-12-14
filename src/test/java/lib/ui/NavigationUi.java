package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUi extends MainPageObject {

    protected static String
            SAVED_LIST_BUTTON;
    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    public void clickSavedList() {
        this.waitForElementAndClick(
                SAVED_LIST_BUTTON,
                "Cannot find 'Saved' button",
                5
        );
    }

}
