package tests.iOS;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testVerifyTextSearchField() {
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
        WelcomePageObject.clickScipIOS();
        WelcomePageObject.assertElementHasText("Search Wikipedia");
    }
}
