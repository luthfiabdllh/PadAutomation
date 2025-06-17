package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.TestContext;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CheckoutSteps {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private ShoppingCartPage cartPage;
    private CheckoutPage checkoutPage;
    private ExtentTest test;
    private final TestContext testContext;

    public CheckoutSteps(TestContext context) {
        this.testContext = context;
        driver = testContext.getDriver();
        homePage = new HomePage(testContext.getDriver());
        loginPage = new LoginPage(testContext.getDriver());
        cartPage = new ShoppingCartPage(testContext.getDriver());
        checkoutPage = new CheckoutPage(testContext.getDriver());
        test = ExtentReportManager.getInstance().createTest("Checkout Test");
    }

    @Given("the user is logged in and has items in the shopping cart")
    public void userIsLoggedInAndHasItemsInCart() {
        homePage.goToHomePage();
        homePage.clickMyAccountDropdown();
        loginPage.enterEmail("testuser3@lambda.com");
        loginPage.enterPassword("Password123");
        loginPage.clickLoginButton();
        homePage.clickHomeLink();
        homePage.addFirstAvailableProductToCart();
        homePage.clickCartIcon();
        test.info("User logged in and added items to cart");
    }

    @And("the user is on the checkout page")
    public void userIsOnCheckoutPage() {
        homePage.clickCheckout();
        test.info("Navigated to checkout page: https://ecommerce-playground.lambdatest.io/index.php?route=checkout/checkout");
    }

    @When("the user enters valid address details")
    public void userEntersValidAddressDetails(DataTable dataTable) {
        checkoutPage.clickNewAddressRadio();
        List<Map<String, String>> addressData = dataTable.asMaps();
        Map<String, String> address = addressData.get(0);
        checkoutPage.enterAddressDetails(
                address.get("FirstName"),
                address.get("LastName"),
                address.get("Address"),
                address.get("City"),
                address.get("PostCode"),
                address.get("Country"),
                address.get("Region")
        );
        test.info("Entered valid address details: " + address);
    }

    @And("the user clicks {string}")
    public void userClicksTermsConditions(String terms) {
        if (terms.equals("I have read and agree to Terms & Conditions")) {
            checkoutPage.clickTermsConditions();
            test.info("Clicked " + terms);
        }
    }

    @And("the user clicks continue button")
    public void userClicksContinueButton() {
        checkoutPage.clickContinueButton();
        test.info("Clicked continue button");
    }

    @And("the user clicks the confirm order button")
    public void userClicksConfirmOrderButton() {
        checkoutPage.clickConfirmOrderButton();
        test.info("Clicked confirm order button");
    }

    @Then("the user should be redirected to the order success page")
    public void userShouldBeRedirectedToOrderSuccessPage() {
        String expectedUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/success";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe("https://ecommerce-playground.lambdatest.io/index.php?route=checkout/success"));

        String actualUrl = checkoutPage.getCurrentUrl();
        if (expectedUrl.equals(actualUrl)) {
            test.pass("Successfully redirected to order success page: " + actualUrl);
        } else {
            test.fail("Failed to redirect to order success page. Actual URL: " + actualUrl);
        }
        Assert.assertEquals(expectedUrl, actualUrl);
        driver.quit();
    }

    @When("the user enters address details with empty address")
    public void userEntersValidAddressDetailsWithEmptyAddressField(DataTable dataTable) {
        checkoutPage.clickNewAddressRadio();
        List<Map<String, String>> addressData = dataTable.asMaps();
        Map<String, String> address = addressData.get(0);
        checkoutPage.enterAddressDetails(
                address.get("FirstName"),
                address.get("LastName"),
                address.get("Address"),
                address.get("City"),
                address.get("PostCode"),
                address.get("Country"),
                address.get("Region")
        );
        test.info("Entered valid address details: " + address);
    }

    @Then("the user should see an error message 'Address 1 must be between 3 and 128 characters!'")
    public void userShouldSeeErrorMessage(String expectedError) {
        String actualError = checkoutPage.getErrorMessage();
        if (actualError.contains(expectedError)) {
            test.pass("Error message displayed as expected: " + actualError);
        } else {
            test.fail("Expected error message: " + expectedError + ", but got: " + actualError);
        }
        Assert.assertTrue(actualError.contains(expectedError));
        driver.quit();
    }


    @Given("the user is logged in but has an empty shopping cart")
    public void userIsLoggedInWithEmptyCart() {
        homePage.goToHomePage();
        homePage.clickMyAccountDropdown();
        loginPage.enterEmail("testuser3@lambda.com");
        loginPage.enterPassword("Password123");
        loginPage.clickLoginButton();
        homePage.clickHomeLink();
        test.info("User logged in with empty shopping cart");
    }

    @When("the user navigates to the checkout page")
    public void userNavigatesToCheckoutPage() {
        homePage.clickCartIcon();
        homePage.clickCheckout();
        test.info("Attempted to navigate to checkout page");
    }

    @Then("the user should be redirected to the shopping cart page with message {string}")
    public void userShouldBeRedirectedToCartPageWithMessage(String expectedMessage) {
        String expectedUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement contentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));
        WebElement messageElement = contentElement.findElement(By.tagName("p")); //

        String actualMessage = messageElement.getText();
        String actualUrl = checkoutPage.getCurrentUrl();

        // Verifikasi
        if (actualUrl.contains(expectedUrl) && actualMessage.contains(expectedMessage)) {
            test.pass("Redirected to shopping cart page with message: " + actualMessage);
        } else {
            test.fail("Expected URL: " + expectedUrl + ", Message: " + expectedMessage +
                    ", but got URL: " + actualUrl + ", Message: " + actualMessage);
        }
        Assert.assertTrue(actualUrl.contains(expectedUrl));
        Assert.assertTrue(actualMessage.contains(expectedMessage));

        driver.quit();
    }

    @When("the user enters address details with empty first and last name")
    public void userEntersValidAddressDetailsWithEmptyFirstAndLast(DataTable dataTable) {
        checkoutPage.clickNewAddressRadio();
        List<Map<String, String>> addressData = dataTable.asMaps();
        Map<String, String> address = addressData.get(0);
        checkoutPage.enterAddressDetails(
                address.get("FirstName"),
                address.get("LastName"),
                address.get("Address"),
                address.get("City"),
                address.get("PostCode"),
                address.get("Country"),
                address.get("Region")
        );
        test.info("Entered valid address details: " + address);
    }

    @Then("the user should see an error message \"Warning: you must agree to Terms & Conditions\"")
    public void userShouldSeeErrorMessage4(String expectedError) {
        String actualError = checkoutPage.getErrorMessage();
        if (actualError.contains(expectedError)) {
            test.pass("Error message displayed as expected: " + actualError);
        } else {
            test.fail("Expected error message: " + expectedError + ", but got: " + actualError);
        }
        Assert.assertTrue(actualError.contains(expectedError));
        driver.quit();
    }


    @And("the user don't click \"{string}\"")
    public void userDontClickTermsConditions(String terms) {
        if (terms.equals("I have read and agree to Terms & Conditions")) {
            test.info("Clicked " + terms);
        }
    }
    @Then("the user should see an error message \"Warning: you must agree to Terms & Conditions\"")
    public void userShouldSeeErrorMessage5(String expectedError) {
        String actualError = checkoutPage.getErrorMessage();
        if (actualError.contains(expectedError)) {
            test.pass("Error message displayed as expected: " + actualError);
        } else {
            test.fail("Expected error message: " + expectedError + ", but got: " + actualError);
        }
        Assert.assertTrue(actualError.contains(expectedError));
        driver.quit();
    }

}