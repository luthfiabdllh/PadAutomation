Feature: Subscription via Cart Page

  Scenario: Verify user can subscribe via the cart page
    Given User launches the browser
    And User navigates to "https://automationexercise.com"
    Then Home page should be visible
    When User clicks on 'Cart' button
    And User scrolls down to footer
    Then 'SUBSCRIPTION' text should be visible
    When User enters email "test@example.com" and clicks the arrow button
    Then Success message 'You have been successfully subscribed!' should be visible
