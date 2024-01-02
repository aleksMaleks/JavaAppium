package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUi extends NavigationUi {

    static {
        SAVED_LIST_BUTTON = "id:Saved";
    }

    public IOSNavigationUi(RemoteWebDriver driver) {
        super(driver);
    }
}
