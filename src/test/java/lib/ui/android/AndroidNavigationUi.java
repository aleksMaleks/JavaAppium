package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUi extends NavigationUi {

    static {
        SAVED_LIST_BUTTON = "id:org.wikipedia:id/nav_tab_reading_lists";
    }

    public AndroidNavigationUi(RemoteWebDriver driver) {
        super(driver);
    }
}
