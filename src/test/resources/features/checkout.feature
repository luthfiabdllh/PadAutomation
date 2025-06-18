 Feature: Checkout Functionality
  As a logged-in user of the e-commerce playground
  I want to complete the checkout process
  So that I can place an order successfully

  Scenario: Successful checkout with valid address details
    Given the user is logged in and has items in the shopping cart
    And the user is on the checkout page
    When the user enters valid address details
      | FirstName | LastName | Address         | City       | PostCode | Country   | Region     |
      | Wahhab    | Awaludin | 123 Yogyakarta  | Yogyakarta | 10001    | Indonesia | Yogyakarta |
    And the user clicks "I have read and agree to Terms & Conditions"
    And the user clicks continue button
    And the user clicks the confirm order button
    Then the user should be redirected to the order success page

  Scenario: Failed checkout with empty shopping cart
    Given the user is logged in but has an empty shopping cart
    When the user navigates to the checkout page
    Then the user should be redirected to the shopping cart page with message "Your shopping cart is empty!"

  Scenario: Checkout with empty address field and valid other details
     Given the user is logged in and has items in the shopping cart
     And the user is on the checkout page
     When the user enters address details with empty address
       | FirstName | LastName | Address | City     | PostCode | Country       | Region   |
       | Wahhab    | Awaludin |         | Yogyakarta | 10001    | Indonesia | Yogyakarta |
     And the user clicks "I have read and agree to Terms & Conditions"
     And the user clicks continue button
     Then the user should see an error message "Address 1 must be between 3 and 128 characters!"

  Scenario: Checkout with empty first & last name field, and valid other details
     Given the user is logged in and has items in the shopping cart
     And the user is on the checkout page
     When the user enters address details with empty first and last name
       | FirstName | LastName | Address         | City       | PostCode | Country   | Region     |
       |           |          | 123 Yogyakarta  | Yogyakarta | 10001    | Indonesia | Yogyakarta |
     And the user clicks "I have read and agree to Terms & Conditions"
     And the user clicks continue button
     Then the user should see an error message "Warning: you must agree to Terms & Conditions"

  Scenario: Checkout without agree to Terms & Conditions
     Given the user is logged in and has items in the shopping cart
     And the user is on the checkout page
     When the user enters valid address details
       | FirstName | LastName | Address         | City       | PostCode | Country   | Region     |
       | Wahhab    | Awaludin | 123 Yogyakarta  | Yogyakarta | 10001    | Indonesia | Yogyakarta |
     And the user don't click "I have read and agree to Terms & Conditions"
     And the user clicks continue button
     Then the user should see an error message "Warning: you must agree to Terms & Conditions"