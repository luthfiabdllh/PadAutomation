package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // --- Locators ---
    @FindBy(id = "input-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "input-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-telephone")
    private WebElement telephoneInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(id = "input-confirm")
    private WebElement passwordConfirmInput;

    @FindBy(css = "label[for='input-agree']")
    private WebElement privacyPolicyLabel;

    @FindBy(css = "input[value='Continue']")
    private WebElement continueButton;

    // Locator untuk peringatan umum di bagian atas
    @FindBy(css = "div.alert-danger")
    private WebElement generalWarningMessage;

    // Locator untuk pesan error spesifik di bawah field
    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div[@class='text-danger']")
    private WebElement firstNameErrorMessage;

    @FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div[@class='text-danger']")
    private WebElement passwordConfirmErrorMessage;

    // --- Constructor ---
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // --- Metode Aksi ---
    public void goToRegisterPage() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
    }
    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }
    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }
    public void enterTelephone(String telephone) {
        telephoneInput.sendKeys(telephone);
    }
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }
    public void enterPasswordConfirm(String passwordConfirm) {
        passwordConfirmInput.sendKeys(passwordConfirm);
    }
    public void checkPrivacyPolicy() {
        privacyPolicyLabel.click();
    }
    public void clickContinueButton() {
        continueButton.click();
    }

    // Metode untuk mendapatkan pesan error dari berbagai kemungkinan lokasi
    public String getDisplayedErrorMessage() {
        try {
            if (generalWarningMessage.isDisplayed()) {
                return wait.until(ExpectedConditions.visibilityOf(generalWarningMessage)).getText();
            }
        } catch (Exception ignored) {}
        try {
            if (firstNameErrorMessage.isDisplayed()) {
                return wait.until(ExpectedConditions.visibilityOf(firstNameErrorMessage)).getText();
            }
        } catch (Exception ignored) {}
        try {
            if (passwordConfirmErrorMessage.isDisplayed()) {
                return wait.until(ExpectedConditions.visibilityOf(passwordConfirmErrorMessage)).getText();
            }
        } catch (Exception ignored) {}
        return "Error message not found.";
    }
}