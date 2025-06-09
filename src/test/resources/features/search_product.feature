Feature: Search product

  Scenario: User can search for a product
    Given User launches the browser
    And User navigates to "https://automationexercise.com"
    Then Home page should be visible
    When User clicks on 'Products' button
    Then User should be navigated to 'All Products' page
    When User enters "dress" in search input and clicks search button
    Then 'Searched Products' section should be visible
    And All the products related to "dress" should be visible
