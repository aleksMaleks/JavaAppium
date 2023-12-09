package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

public class CoreTestCase extends TestCase {

    private static String PLATFORM_IOS = "ios";
    private static String PLATFORM_ANDROID = "android";

    private static String PLATFORM_ENV = System.getenv("PLATFORM");

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = getCapabilitiesPlatformEnv();

        driver = getDriverPlatformEnv(capabilities);
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(LANDSCAPE);
    }

    protected void backgroundApp(Duration seconds) {
        driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesPlatformEnv() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (PLATFORM_ENV.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
            capabilities.setCapability("appium:platformVersion", "8.1.0");
            capabilities.setCapability("appium:automationName", "UiAutomator2");
            capabilities.setCapability("appium:appPackage", "org.wikipedia");
            capabilities.setCapability("appium:appActivity", ".main.MainActivity");
            capabilities.setCapability(
                    "appium:app",
                    "/Users/aleksandrafonin/Desktop/JavaAppium/apks/org.wikipedia.apk");
        } else if (PLATFORM_ENV.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("appium:deviceName", "iPhone 13_1");
            capabilities.setCapability("appium:platformVersion", "17.0");
            capabilities.setCapability("appium:automationName", "XCUITest");
            capabilities.setCapability(
                    "appium:app",
                    "/Users/aleksandrafonin/Desktop/JavaAppium/apks/Wikipedia.app");
        } else {
            throw new Exception("Cannot get run platform from enc variable. Platform value " + PLATFORM_ENV);
        }
        return capabilities;
    }

    private AppiumDriver getDriverPlatformEnv(DesiredCapabilities capabilities) throws Exception {
        if (PLATFORM_ENV.equals(PLATFORM_ANDROID)) {
            return driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        } else if (PLATFORM_ENV.equals(PLATFORM_IOS)) {
            return driver = new IOSDriver(new URL(AppiumURL), capabilities);
        } else {
            throw new Exception("Cannot get run platform from enc variable. Platform value " + PLATFORM_ENV);
        }
    }
}
