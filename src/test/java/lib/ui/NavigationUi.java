package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUi extends MainPageObject {

    private static final String
            SAVED_LIST_BUTTON = "org.wikipedia:id/nav_tab_reading_lists";
    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    public void clickSavedList() {
        this.waitForElementAndClick(
                By.id(SAVED_LIST_BUTTON),
                "Cannot find 'Saved' button",
                5
        );
    }

}
