package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

public class CoreTestCase {


    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePage();
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() {
        driver.quit();
    }

    @Step("Rotate screen to portrait mode")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "
                    + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Rotate screen to landscape mode")
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "
                    + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Send mobile app to background (this method does nothing for Mobile Web)")
    protected void backgroundApp(Duration seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "
                    + Platform.getInstance().getPlatformVar());
        }

    }

    @Step("Open Wikipedia URL for Mobile Web (this method does nothing for Android and iOs)")
    protected void openWikiWebPageForMobileWeb() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "
                    + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip welcome page screen for iOs")
    private void skipWelcomePage() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            if (Platform.getInstance().isIOS()) {
                WelcomePageObject.clickScipIOS();
            } else if (Platform.getInstance().isAndroid()) {
                WelcomePageObject.clickScipAndroid();
            }
        }
    }

    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties  file");
            e.printStackTrace();
        }
    }
}
