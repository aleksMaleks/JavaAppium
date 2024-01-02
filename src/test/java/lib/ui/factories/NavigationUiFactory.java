package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUi;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidNavigationUi;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IOSMyListPageObject;
import lib.ui.ios.IOSNavigationUi;
import lib.ui.ios.IOSSearchPageObject;
import lib.ui.mobile_web.MWMyListPageObject;
import lib.ui.mobile_web.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUiFactory {

    public static NavigationUi get(RemoteWebDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUi(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSNavigationUi(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}
