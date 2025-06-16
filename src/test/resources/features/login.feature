@login
Feature: SC-001 - User Login Functionality

  As an unauthenticated user
  I want to be able to log into my account
  So that I can access my personal account page

  Background:
    Given the user is on the login page
    # (URL: https://ecommerce-playground.lambdatest.io/index.php?route=account/login)
  @positive
  Scenario: TC-001 - Login with a valid email and password
    When the user fills in the email field with "wahhaab@lambda.com"
    And the user fills in the password field with "1234567890"
    And the user clicks the Login button
    Then the user is successfully logged in and redirected to the account page
    #(URL: https://ecommerce-playground.lambdatest.io/index.php?route=account/account)

  @negative
  Scenario: TC-002 - Login with valid email but wrong password
    When the user fills in the email field with "testuser@lambda.com"
    And the user fills in the password field with "WrongPass"
    And the user clicks the Login button
    Then the error message "Warning: E-Mail Address is already registered!" should be displayed

  @negative
  Scenario: TC-003 - Login with an invalid email format
    When the user fills in the email field with "invalid.email"
    And the user fills in the password field with "Password123"
    And the user clicks the Login button
    Then the error message "Warning: No match for E-Mail Address and/or Password." should be displayed

  @negative
  Scenario: TC-004 - Login with an empty email field
    When the user fills in the email field with ""
    And the user fills in the password field with "Password123"
    And the user clicks the Login button
    Then the error message "Warning: E-Mail Address is already registered!" should be displayed

  @negative
  Scenario: TC-005 - Login with an empty password field
    When the user fills in the email field with "testuser@lambda.com"
    And the user fills in the password field with ""
    And the user clicks the Login button
    Then the error message "Warning: E-Mail Address is already registered!" should be displayed