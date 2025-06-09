package stepsDefinition;

import Pages.CartPage;
import io.cucumber.java.en.When;

public class CartSubscriptionSteps {

    CartPage cartPage;

    @When("User clicks on 'Cart' button")
    public void user_clicks_on_cart_button() {
        cartPage = new CartPage(CommonSteps.driver);
        cartPage.clickCartButton();
    }
}
