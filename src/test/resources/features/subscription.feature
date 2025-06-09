Feature: Subscription functionality

  Scenario: User can subscribe with email successfully
    Given User launches the browser
    And User navigates to "https://automationexercise.com"
    Then Home page should be visible
    When User scrolls down to footer
    Then 'SUBSCRIPTION' text should be visible
    When User enters email "testuser@example.com" and clicks the arrow button
    Then Success message 'You have been successfully subscribed!' should be visible
