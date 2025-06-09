package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCartButton() {
        driver.findElement(By.cssSelector("a[href='/view_cart']")).click();
    }
}
