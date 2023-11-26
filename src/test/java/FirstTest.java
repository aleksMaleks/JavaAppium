import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
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
                "Cannot find 'Navigate up' to cancel search",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' to cancel search",
                5
        );


        waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find 'Saved' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='TestList']"),
                "Cannot find 'Saved' button",
                5
        );

//        waitForElementNotPresent(
//                By.xpath("//*[@class='android.view.ViewGroup']" +
//                        "//*[@text='Java (programming language)']"),
//                "Search article is still present on the page",
//                3
//        );
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
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                3
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView']" +
                        "[@text='Appium']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@class='android.view.View'][@content-desc=' is an open source ']"),
                "Cannot find article title",
                5
        );

        swipeUpToFindElement(
                By.xpath("//*[@content-desc='View article in browser']"),
                "Swipe error");

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

    @Test
    public void saveFirstArticleToMyList() {
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

        waitForElementPresent(
                By.xpath("//android.view.View[@content-desc='Java (programming language)']"),
                "Cannot find article title",
                3
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find 'Save' button",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'Add to list' button",
                5
        );


        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "TestList",
                "Cannot find 'Name list' field",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find 'OK' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' to cancel search",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' to cancel search",
                5
        );


        waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find 'Saved' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='TestList']"),
                "Cannot find saved list 'TestList'",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find 'TestList' article in a save list"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResult() {

//        driver.rotate(ScreenOrientation.PORTRAIT);

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

//        WebElement searchTextBox = waitForElementPresent(
//                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
//                "Cannot find WebElement"
//        );


//        String title = searchTextBox.getAttribute("name");
//        System.out.println(title);



        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "contentDescription",
                "Cannot find title of article ",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "contentDescription",
                "Cannot find title of article ",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "contentDescription",
                "Cannot find title of article ",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {
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
                "Cannot find 'Search Wikipedia' input",
                3
        );

        driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@class='android.view.ViewGroup']" +
                        "//*[@text='Java (programming language)']"),
                "Cannot find article after returning from background",
                3
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return  wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return  waitForElementPresent(by, error_message, 5);
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

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(2000);
    }

    protected void swipeUpToFindElement(By by, String error_message) {
        if (!driver.findElement(by).isEnabled()) {

            System.out.println("Is Displayed!");
        } else {
            swipeUpQuick();
            System.out.println("Is Not Displayed!");
        }
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw  new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribete, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribete);
    }
}
