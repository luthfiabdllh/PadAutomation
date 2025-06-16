package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SuccessPage {

    private final WebDriverWait wait;

    /**
     * Locator yang andal untuk header di halaman sukses.
     * Menggunakan contains() karena di dalam H1 ada elemen <i>.
     */
    @FindBy(xpath = "//h1[contains(., 'Your Account Has Been Created!')]")
    private WebElement successHeader;

    @FindBy(linkText = "Continue")
    private WebElement continueButton;

    public SuccessPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Memeriksa apakah header sukses terlihat.
     * @return true jika header terlihat, false jika tidak.
     */
    public boolean isSuccessHeaderVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}