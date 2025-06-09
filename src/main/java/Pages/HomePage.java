package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get("https://automationexercise.com");
    }

    public boolean isAt() {
        return driver.getTitle().contains("Automation Exercise");
    }

    public void clickSignupLogin() {
        driver.findElement(By.cssSelector("a[href='/login']")).click();
    }

    public void clickProducts() {
        driver.findElement(By.xpath("//a[@href='/products']")).click();
    }
}
