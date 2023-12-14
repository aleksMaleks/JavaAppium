package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;

public class IOSNavigationUi extends NavigationUi {

    static {
        SAVED_LIST_BUTTON = "id:Saved";
    }

    public IOSNavigationUi(AppiumDriver driver) {
        super(driver);
    }
}
