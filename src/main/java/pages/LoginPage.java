package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // --- Locators untuk elemen-elemen di halaman login ---

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(css = "input[value='Login']")
    private WebElement loginButton;

    // Locator untuk pesan error yang muncul setelah login gagal
    @FindBy(css = "div.alert-danger")
    private WebElement errorMessage;

    // Locator untuk verifikasi keberhasilan login (elemen di halaman Akun)
    @FindBy(xpath = "//h2[text()='My Account']")
    private WebElement myAccountHeader;


    // --- Constructor ---
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // --- Metode Aksi (Public methods untuk interaksi) ---

    public void goToLoginPage() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        // Membersihkan field sebelum mengisi, untuk kasus Scenario Outline
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessageText() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    public boolean isMyAccountHeaderVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(myAccountHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}