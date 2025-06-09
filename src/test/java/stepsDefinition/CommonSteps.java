package stepsDefinition;

import Pages.HomePage;
import Pages.SubscriptionSectionPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonSteps {

    public static WebDriver driver;
    public static HomePage homePage;
    public static SubscriptionSectionPage subscriptionPage;

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ExtentTest test;

    @Before
    public void setup(Scenario scenario) {
        test = extent.createTest(scenario.getName());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        test.log(Status.INFO, "Browser launched and maximized.");
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Scenario failed: " + scenario.getName());
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, scenario.getName().replaceAll(" ", "_"));
            try {
                test.addScreenCaptureFromPath(screenshotPath, "Failed Screenshot");
            } catch (Exception e) {
                test.log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
            }
        } else {
            test.log(Status.PASS, "Scenario passed: " + scenario.getName());
        }

        if (driver != null) {
            driver.quit();
            test.log(Status.INFO, "Browser closed.");
        }

        extent.flush();
    }

    @Given("User launches the browser")
    public void user_launches_the_browser() {
        // Already handled in @Before
        test.log(Status.INFO, "Browser launch step executed.");
    }

    @And("User navigates to {string}")
    public void user_navigates_to(String url) {
        driver.get(url);
        test.log(Status.INFO, "Navigated to URL: " + url);
    }

    @Then("Home page should be visible")
    public void home_page_should_be_visible() {
        assertTrue(homePage.isAt(), "Home page is not visible");
        test.log(Status.PASS, "Home page is visible.");
    }

    @When("User scrolls down to footer")
    public void user_scrolls_down_to_footer() {
        subscriptionPage = new SubscriptionSectionPage(driver);
        subscriptionPage.scrollToFooter();
        test.log(Status.INFO, "Scrolled down to footer.");
    }

    @Then("'SUBSCRIPTION' text should be visible")
    public void subscription_text_should_be_visible() {
        assertTrue(subscriptionPage.isSubscriptionTextVisible(), "'SUBSCRIPTION' text is not visible");
        test.log(Status.PASS, "'SUBSCRIPTION' text is visible.");
    }

    @When("User enters email {string} and clicks the arrow button")
    public void user_enters_email_and_clicks_the_arrow_button(String email) {
        subscriptionPage.enterEmailAndSubscribe(email);
        test.log(Status.INFO, "Entered email and clicked subscribe: " + email);
    }

    @Then("Success message 'You have been successfully subscribed!' should be visible")
    public void success_message_should_be_visible() {
        assertTrue(subscriptionPage.isSuccessMessageVisible(), "Success message is not visible");
        test.log(Status.PASS, "Success message is visible.");
    }
}
