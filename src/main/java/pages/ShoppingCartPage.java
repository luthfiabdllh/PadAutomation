package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
    private WebDriver driver;

    @FindBy(linkText = "Checkout")
    private WebElement checkoutButton;


    @FindBy(css = ".text-center")
    private WebElement emptyCartMessage;

    @FindBy(css = "cart-icon")
    private  WebElement cart;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToCartPage() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart");
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public String getEmptyCartMessage() {
        return emptyCartMessage.isDisplayed() ? emptyCartMessage.getText() : "";
    }
}