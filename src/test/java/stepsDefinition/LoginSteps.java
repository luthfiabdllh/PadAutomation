package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import utils.TestContext;

public class LoginSteps {

    private LoginPage loginPage;
    private TestContext testContext;

    public LoginSteps(TestContext context) {
        this.testContext = context;
        this.loginPage = new LoginPage(testContext.getDriver());
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage.goToLoginPage();
        testContext.getTest().info("Navigated to the login page.");
    }

    @When("the user fills in the email field with {string}")
    public void theUserFillsInTheEmailFieldWith(String email) {
        loginPage.enterEmail(email);
        testContext.getTest().info("Entered email: " + email);
    }

    @And("the user fills in the password field with {string}")
    public void theUserFillsInThePasswordFieldWith(String password) {
        loginPage.enterPassword(password);
        testContext.getTest().info("Entered password.");
    }

    @And("the user clicks the {string} button")
    public void theUserClicksTheButton(String buttonName) {
        // Logika ini bisa diperluas jika ada tombol lain, tapi untuk sekarang langsung ke login
        if (buttonName.equalsIgnoreCase("Login")) {
            loginPage.clickLoginButton();
            testContext.getTest().info("Clicked the Login button.");
        } else {
            // Bisa ditambahkan error handling jika tombol lain disebut
            throw new IllegalArgumentException(buttonName + " button is not defined in this step.");
        }
    }

    @Then("the user is successfully logged in and redirected to the account page")
    public void theUserIsSuccessfullyLoggedInAndRedirectedToTheAccountPage() {
        boolean isRedirected = loginPage.isMyAccountHeaderVisible();
        Assertions.assertTrue(isRedirected, "Verification failed: User was not redirected to the account page.");
        if (isRedirected) {
            testContext.getTest().pass("Successfully logged in and redirected to the Account page.");
        }
    }

    @Then("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.getErrorMessageText();
        boolean isMessageCorrect = actualErrorMessage.contains(expectedErrorMessage);

        Assertions.assertTrue(isMessageCorrect,
                "Error message mismatch. Expected to contain: '" + expectedErrorMessage + "', but got: '" + actualErrorMessage + "'");

        if (isMessageCorrect) {
            testContext.getTest().pass("Correct error message was displayed: " + actualErrorMessage);
        }
    }
}