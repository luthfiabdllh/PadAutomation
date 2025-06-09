package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAllProductsPageVisible() {
        return driver.findElement(By.xpath("//h2[text()='All Products']")).isDisplayed();
    }

    public void searchProduct(String productName) {
        driver.findElement(By.id("search_product")).sendKeys(productName);
        driver.findElement(By.id("submit_search")).click();
    }

    public boolean isSearchedProductsVisible() {
        return driver.findElement(By.xpath("//h2[text()='Searched Products']")).isDisplayed();
    }

    public boolean areSearchResultsVisible() {
        List<?> results = driver.findElements(By.cssSelector(".productinfo.text-center p"));
        return results.size() > 0;
    }
}
