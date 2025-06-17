package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Opsi utama: XPath berdasarkan teks "My account"
    @FindBy(xpath = "//a[contains(text(), 'My account')]")
    private WebElement myAccountLink;

    // Opsi alternatif 1: CSS Selector berdasarkan kelas
    @FindBy(css = "a.nav-link.dropdown-toggle")
    private WebElement myAccountDropdown;

    // Opsi alternatif 2: XPath berdasarkan href
    @FindBy(xpath = "//a[contains(@href, 'route=account/account')]")
    private WebElement myAccountLinkByHref;

    // Opsi alternatif 3: Partial Link Text
    @FindBy(partialLinkText = "My account")
    private WebElement myAccountLinkByPartialText;

    @FindBy(xpath = "//a[contains(@href, 'route=account/login')]")
    private WebElement loginLink;

    @FindBy(css = "a[href*='route=common/home']")
    private WebElement homeLink;

    @FindBy(css = "a[href*='route=checkout/checkout']")
    private WebElement checkoutLink;

    @FindBy(css = "div.cart-icon")
    private WebElement cartIcon;

    @FindBy(css = "div.cart-icon .cart-item-total")
    private WebElement cartItemTotal;

    @FindBy(css = ".swiper-slide-active .product-thumb") // Fokus pada slide aktif
    private List<WebElement> activeProductList;

    @FindBy(css = ".btn.btn-cart") // Tombol "Add to Cart" spesifik
    private WebElement addToCartButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToHomePage() {
        driver.get("https://ecommerce-playground.lambdatest.io/");
    }

    public void clickMyAccountDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountLinkByPartialText)).click();
    }


    public void clickHomeLink() {
        wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
    }

    public void clickCartIcon() {
        // Tunggu elemen "cartIcon" terlihat
        WebElement cartButton = wait.until(ExpectedConditions.visibilityOf(cartIcon));

        // Gulir ke ikon keranjang untuk memastikan terlihat
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartButton);

        // Tunggu elemen menjadi clickable dan klik
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        } catch (Exception e) {
            // Fallback: Gunakan JavaScript untuk klik jika gagal
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartIcon);
        }
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutLink)).click();
    }


    public void addFirstAvailableProductToCart() {
        // Tunggu hingga carousel dimuat dan slide aktif tersedia
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".swiper-slide-active")));

        // Ambil produk pertama dari slide aktif
        wait.until(ExpectedConditions.visibilityOfAllElements(activeProductList));
        WebElement firstProduct = activeProductList.get(5);

        // Gulir ke elemen produk untuk memastikan terlihat
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);

        // Coba hover untuk menampilkan tombol "Add to Cart"
        actions.moveToElement(firstProduct).perform();
        try {
            // Tunggu tombol "Add to Cart" muncul setelah hover
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".swiper-slide-active .btn.btn-cart")));
            addButton.click();
        } catch (Exception e) {
            // Jika hover gagal atau tombol tidak muncul, klik produk lalu tambahkan
            firstProduct.findElement(By.cssSelector("a")).click(); // Klik tautan produk
            // Tunggu tombol "Add to Cart" muncul di halaman detail
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".btn.btn-cart")));
            addButton.click();
        }

        // Tunggu konfirmasi (misalnya, perubahan ikon keranjang)
        wait.until(ExpectedConditions.textToBePresentInElement(cartItemTotal, "1")); // Asumsi jumlah item berubah
    }

    public String getCartItemTotal() {
        wait.until(ExpectedConditions.visibilityOf(cartItemTotal));
        return cartItemTotal.getText();
    }




}