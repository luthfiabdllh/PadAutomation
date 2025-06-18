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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);

        try {
            // 1. Tunggu hingga carousel dimuat
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".swiper-slide-active")
            ));

            // 2. Cari semua produk dalam slide aktif
            By productSelector = By.cssSelector(".swiper-slide-active .product-thumb");
            List<WebElement> activeProducts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productSelector));

            // 3. Verifikasi jumlah produk
            System.out.println("[DEBUG] Jumlah produk ditemukan: " + activeProducts.size());
            if (activeProducts.size() < 3) {
                throw new RuntimeException("Produk indeks ke-2 tidak tersedia. Hanya ditemukan " + activeProducts.size() + " produk");
            }

            // 4. Ambil produk ketiga (indeks 2)
            WebElement thirdProduct = activeProducts.get(2);

            // 5. Scroll ke produk
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                    thirdProduct
            );
            Thread.sleep(1000); // Beri waktu untuk scroll

            // 6. Coba hover dan klik tombol
            actions.moveToElement(thirdProduct).pause(2000).perform();

            try {
                // Cari tombol ADD TO CART spesifik untuk produk ini
                WebElement addButton = thirdProduct.findElement(By.cssSelector(".btn.btn-cart"));
                wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
                System.out.println("Berhasil menambahkan produk dari carousel");
            }
            catch (Exception e) {
                // Fallback: Buka halaman detail produk
                System.out.println("Fallback ke halaman detail: " + e.getMessage());
                thirdProduct.findElement(By.cssSelector(".title a")).click(); // Klik judul produk

                // Tunggu dan klik tombol di halaman detail
                WebElement addButtonDetail = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("#product button[type='button'][id='button-cart']")
                ));
                addButtonDetail.click();
            }
        }
        catch (Exception e) {
            System.err.println("Gagal menambahkan produk: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getCartItemTotal() {
        wait.until(ExpectedConditions.visibilityOf(cartItemTotal));
        return cartItemTotal.getText();
    }




}