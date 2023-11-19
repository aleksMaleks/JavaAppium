import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;


    @Before public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.1.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability(
                "app", "/Users/aleksandrafonin/Desktop/JavaAppium/apks/org.wikipedia.apk");
        capabilities.setCapability("noReset", "true");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }


    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void firstTest(){
        System.out.println("First test run");
    }

    @Test
    public void verifySearchResults() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                3
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                3
        );

        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find 'Java (programming language)' topic searching by 'Java'"
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                3
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                3
        );

        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find 'Java (programming language)' topic searching by 'Java'"
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X to cancel search",
                5
        );

        waitForElementNotPresent(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Java (programming language)']"),
                "Search article is still present on the page",
                3
        );
    }

    @Test
    public void testVerifySearchResult() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                3
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "java",
                "Cannot find search input",
                3
        );

        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find 'Java (programming language)' topic searching by 'Java'"
        );

        List<WebElement> search_results = driver.findElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']" +
                        "//*[@class='android.view.ViewGroup']"));

        for (WebElement search_result : search_results) {
            WebElement result = search_result.findElement(
                    By.xpath("//*[contains(@text,'Java')]"));
            Assert.assertTrue(
                    "Search result doesn't contain 'Java'",
                    result.getText().contains("Java")
            );
        }
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                3
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                3
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find 'Search Wikipedia' input",
                3
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//android.view.View[@content-desc='Java (programming language)']"),
                "Cannot find article title",
                3
        );

        String article_title = title_element.getAttribute("name");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void verifyTextSearchField() {
        assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']" +
                        "//*[@class='android.widget.TextView']"),
                "Search Wikipedia",
                "Cannot find 'Search Wikipedia' input"
        );
    }

    private void assertElementHasText(By by, String expected_text, String error_message) {
        WebElement web_element = waitForElementPresent(
                by,
                error_message,
                5
        );

        String actual_text = web_element.getAttribute("text");

        Assert.assertEquals(
                error_message,
                expected_text,
                actual_text
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return  wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return  waitForElementPresent(by, error_message, 3);
    }

    private void waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
    }

    private void waitForElementAndSendKeys(By by, String value, String error_message,
                                                 long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return  wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return  element;
    }
}
