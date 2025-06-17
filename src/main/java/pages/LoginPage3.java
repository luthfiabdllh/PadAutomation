package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage3 {



        private WebDriver driver;

        @FindBy(id = "input-email")
        private WebElement emailField;

        @FindBy(id = "input-password")
        private WebElement passwordField;

        @FindBy(xpath = "//input[@value='Login']")
        private WebElement loginButton;

        @FindBy(css = ".alert.alert-danger.alert-dismissible")
        private WebElement errorMessage;

        public LoginPage3(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void enterEmail(String email) {
            emailField.clear();
            if (!email.equals("(kosong)")) {
                emailField.sendKeys(email);
            }
        }

        public void enterPassword(String password) {
            passwordField.clear();
            if (!password.equals("(kosong)")) {
                passwordField.sendKeys(password);
            }
        }

        public void clickLoginButton() {
            loginButton.click();
        }


}
