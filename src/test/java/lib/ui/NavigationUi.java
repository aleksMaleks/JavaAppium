package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUi extends MainPageObject {

    private static final String
            SAVED_LIST_BUTTON = "id:org.wikipedia:id/nav_tab_reading_lists";
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
