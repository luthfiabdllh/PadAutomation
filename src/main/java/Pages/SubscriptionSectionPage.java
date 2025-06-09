package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SubscriptionSectionPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SubscriptionSectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public boolean isSubscriptionTextVisible() {
        return driver.findElement(By.xpath("//h2[text()='Subscription']")).isDisplayed();
    }

    public void enterEmailAndSubscribe(String email) {
        driver.findElement(By.id("susbscribe_email")).sendKeys(email);
        driver.findElement(By.id("subscribe")).click();
    }

    public boolean isSuccessMessageVisible() {
        return wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//div[text()='You have been successfully subscribed!']")))
                .isDisplayed();
    }
}
