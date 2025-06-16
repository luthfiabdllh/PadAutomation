@login
Feature: SC-001 - User Login Functionality

  As an unauthenticated user
  I want to be able to log into my account
  So that I can access my personal account page

  Background:
    Given the user is on the login page
  (URL: https://ecommerce-playground.lambdatest.io/index.php?route=account/login)

  @positive
  Scenario: TC-001 - Login with a valid email and password
    When the user fills in the email field with "testuser@lambda.com"
    And the user fills in the password field with "Password123"
    And the user clicks the "Login" button
    Then the user is successfully logged in and redirected to the account page
  (URL: https://ecommerce-playground.lambdatest.io/index.php?route=account/account)

  @negative
  Scenario Outline: TC-002 to TC-005 - Login with various invalid inputs
    When the user fills in the email field with "<email>"
    And the user fills in the password field with "<password>"
    And the user clicks the "Login" button
    Then the error message "Warning: No match for E-Mail Address and/or Password." should be displayed

    Examples:
      | id     | email                 | password      | test_case_description             |
      | TC-002 | "testuser@lambda.com" | "WrongPass"   | Login with valid email, wrong password |
      | TC-003 | "invalid.email"       | "Password123" | Login with an invalid email format   |
      | TC-004 | ""                    | "Password123" | Login with an empty email field      |
      | TC-005 | "testuser@lambda.com" | ""            | Login with an empty password field   |