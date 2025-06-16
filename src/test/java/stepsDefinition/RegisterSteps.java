package stepsDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.RegisterPage;
import pages.SuccessPage;
import utils.TestContext;

public class RegisterSteps {

    private final RegisterPage registerPage;
    private final TestContext testContext;
    private final SuccessPage successPage;

    public RegisterSteps(TestContext context) {
        this.testContext = context;
        this.registerPage = new RegisterPage(testContext.getDriver());
        this.successPage = new SuccessPage(testContext.getDriver());
    }

    @Given("the user is on the registration page")
    public void theUserIsOnTheRegistrationPage() {
        registerPage.goToRegisterPage();
        testContext.getTest().info("Navigated to the registration page.");
    }

    @When("the user fills in First Name with {string}")
    public void theUserFillsInFirstNameWith(String firstName) {
        registerPage.enterFirstName(firstName);
    }

    @And("the user fills in Last Name with {string}")
    public void theUserFillsInLastNameWith(String lastName) {
        registerPage.enterLastName(lastName);
    }

    @And("the user fills in Email with {string}")
    public void theUserFillsInEmailWith(String email) {
        registerPage.enterEmail(email);
    }

    @And("the user fills in Telephone with {string}")
    public void theUserFillsInTelephoneWith(String telephone) {
        registerPage.enterTelephone(telephone);
    }

    @And("the user fills in Password with {string} and password confirmation {string}")
    public void theUserFillsInPasswordWithAndPasswordConfirmation(String password, String confirm) {
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirm(confirm);
    }

    @And("the user checks the {string} agreement box")
    public void theUserChecksTheAgreementBox(String policy) {
        // Parameter 'policy' bisa digunakan jika ada beberapa jenis agreement
        registerPage.checkPrivacyPolicy();
    }

    @And("the user does not check the {string} agreement box")
    public void theUserDoesNotCheckTheAgreementBox(String policy) {
        // Tidak melakukan apa-apa, sengaja membiarkan checkbox tidak tercentang
        testContext.getTest().info("Intentionally not checking the Privacy Policy box.");
    }

    @And("the user clicks the Continue button")
    public void theUserClicksTheContinueButton() {
        registerPage.clickContinueButton();
        testContext.getTest().info("Clicked the Continue button.");
    }

    @Then("the user is successfully registered and redirected to the success page")
    public void theUserIsSuccessfullyRegisteredAndRedirectedToTheSuccessPage() {
        // Gunakan objek successPage yang sudah diinisialisasi
        boolean isRedirected = successPage.isSuccessHeaderVisible();
        Assertions.assertTrue(isRedirected, "User was not redirected to the success page or the success message was not found.");
        if (isRedirected) {
            testContext.getTest().pass("Successfully registered and redirected to the Success page.");
        }
    }

    @Then("the error message {string} is displayed")
    public void theErrorMessageIsDisplayed(String expectedError) {
        String actualError = registerPage.getDisplayedErrorMessage();
        Assertions.assertTrue(
                actualError.contains(expectedError),
                "Error message mismatch. Expected to contain: '" + expectedError + "', but got: '" + actualError + "'"
        );
    }

    // Step reusable untuk skenario negatif
    @When("the user fills the registration form with an existing email {string}")
    public void theUserFillsTheRegistrationFormWithAnExistingEmail(String email) {
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        registerPage.enterEmail(email);
        registerPage.enterTelephone("08123456789");
        registerPage.enterPassword("Password123");
        registerPage.enterPasswordConfirm("Password123");
    }

    // Step reusable untuk skenario negatif
    @When("the user fills the registration form with valid and unique data")
    public void theUserFillsTheRegistrationFormWithValidAndUniqueData() {
        String uniqueEmail = "wahhab" + System.currentTimeMillis() + "@lambda.com";
        registerPage.enterFirstName("Wahhab");
        registerPage.enterLastName("Awaludin");
        registerPage.enterEmail(uniqueEmail);
        registerPage.enterTelephone("1234567890");
        registerPage.enterPassword("Pass1234");
        registerPage.enterPasswordConfirm("Pass1234");
        testContext.getTest().info("Filling registration form with unique email: " + uniqueEmail);
    }

    // Step reusable untuk skenario negatif
    @When("the user leaves the First Name field empty")
    public void theUserLeavesTheFirstNameFieldEmpty() {
        registerPage.enterFirstName("");
    }

    // Step reusable untuk skenario negatif
    @When("the user fills in Password with {string} but password confirmation with {string}")
    public void theUserFillsInPasswordWithButPasswordConfirmationWith(String password, String confirm) {
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirm(confirm);
    }

    // Step reusable untuk skenario negatif
    @When("the user fills all registration fields with valid data")
    public void theUserFillsAllRegistrationFieldsWithValidData() {
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        registerPage.enterFirstName("Jane");
        registerPage.enterLastName("Doe");
        registerPage.enterEmail(uniqueEmail);
        registerPage.enterTelephone("08987654321");
        registerPage.enterPassword("Password123");
        registerPage.enterPasswordConfirm("Password123");
    }
}