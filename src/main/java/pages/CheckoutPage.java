package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "input-payment-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-payment-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-payment-address-1")
    private WebElement addressField;

    @FindBy(id = "input-payment-city")
    private WebElement cityField;

    @FindBy(id = "input-payment-postcode")
    private WebElement postCodeField;

    @FindBy(id = "input-payment-country")
    private WebElement countryDropdown;

    @FindBy(id = "input-payment-zone")
    private WebElement regionDropdown;

    @FindBy(id = "input-shipping-method-flat-flat")
    private WebElement shippingMethodRadio;

    @FindBy(id = "input-payment-method-cod-cod")
    private WebElement paymentMethodRadio;

    @FindBy(id = "input-agree")
    private WebElement termsConditionsCheckbox;

    @FindBy(id = "button-save")
    private WebElement continueButton;

    @FindBy(id = "button-confirm")
    private WebElement confirmOrderButton;

    @FindBy(id = "input-payment-address-new")
    private WebElement chooiseAddress;

    @FindBy(css = ".invalid-feedback.d-block")
    private List<WebElement> invalidFeedbackErrors;

    @FindBy(css = ".alert.alert-warning.alert-dismissible")
    private WebElement errorMessage;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        PageFactory.initElements(driver, this);
    }

    public void navigateToCheckoutPage() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=checkout/checkout");
    }

    public void enterAddressDetails(String firstName, String lastName, String address, String city, String postCode, String country, String region) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        addressField.clear();
        if (!address.equals("")) {
            addressField.sendKeys(address);
        }
        cityField.clear();
        cityField.sendKeys(city);
        postCodeField.clear();
        if (!postCode.equals("")) {
            postCodeField.sendKeys(postCode);
        }
        // Pilih negara
        Select countrySelect = new Select(wait.until(ExpectedConditions.visibilityOf(countryDropdown)));
        try {
            countrySelect.selectByVisibleText(country != null ? country : "");
        } catch (NoSuchElementException e) {
            System.out.println("Country option '" + country + "' not found, defaulting to first option.");
            countrySelect.selectByIndex(0); // Fallback ke opsi pertama jika tidak ditemukan
        }

        // Pilih region
        Select regionSelect = new Select(wait.until(ExpectedConditions.visibilityOf(regionDropdown)));
        try {
            regionSelect.selectByVisibleText(region != null ? region : "");
        } catch (NoSuchElementException e) {
            System.out.println("Region option '" + region + "' not found, defaulting to first option.");
            regionSelect.selectByIndex(0); // Fallback ke opsi pertama jika tidak ditemukan
        }
    }

    public void selectShippingMethod() {
        if (shippingMethodRadio.isEnabled()) {
            shippingMethodRadio.click();
        }
    }

    public void clickNewAddressRadio() {
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='input-payment-address-new']")));
        label.click();
    }

    public void selectPaymentMethod(String method) {
        if (method.equals("Cash on Delivery") && paymentMethodRadio.isEnabled()) {
            paymentMethodRadio.click();
        }
    }

    public void clickConfirmOrderButton() {
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-confirm")));
        label.click();


    }
    public void clickTermsConditions() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='input-agree']")));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    public void clickContinueButton() {
        continueButton.click();
    }
    public List<String> getErrorMessages() {
        List<String> errorMessages = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Tunggu sampai pesan error muncul
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".invalid-feedback.d-block, .alert.alert-warning")
            ));

            // Kumpulkan semua pesan error dari invalid-feedback
            for (WebElement error : invalidFeedbackErrors) {
                if (error.isDisplayed()) {
                    errorMessages.add(error.getText().trim());
                }
            }

            // Tambahkan pesan warning jika ada
            if (errorMessage.isDisplayed()) {
                errorMessages.add(errorMessage.getText().replace("×", "").trim());
            }
        } catch (TimeoutException e) {
            // Tidak ada error message yang muncul
        }
        return errorMessages;
    }

    public boolean isErrorMessageDisplayed(String expectedMessage) {
        List<String> actualMessages = getErrorMessages();
        for (String message : actualMessages) {
            if (message.contains(expectedMessage)) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}