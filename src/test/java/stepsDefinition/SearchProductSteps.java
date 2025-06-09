package stepsDefinition;

import Pages.ProductsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchProductSteps {

    ProductsPage productsPage;

    @When("User clicks on 'Products' button")
    public void user_clicks_on_products_button() {
        CommonSteps.homePage.clickProducts();
        productsPage = new ProductsPage(CommonSteps.driver);
    }

    @Then("User should be navigated to 'All Products' page")
    public void user_should_be_navigated_to_all_products_page() {
        assertTrue(productsPage.isAllProductsPageVisible(), "All Products page is not visible");
    }

    @When("User enters {string} in search input and clicks search button")
    public void user_enters_in_search_input_and_clicks_search_button(String productName) {
        productsPage.searchProduct(productName);
    }

    @Then("'Searched Products' section should be visible")
    public void searched_products_section_should_be_visible() {
        assertTrue(productsPage.isSearchedProductsVisible(), "'Searched Products' section is not visible");
    }

    @Then("All the products related to {string} should be visible")
    public void all_the_products_related_to_should_be_visible(String productName) {
        assertTrue(productsPage.areSearchResultsVisible(), "Search results are not visible");
        CommonSteps.driver.quit(); // cleanup
    }
}
