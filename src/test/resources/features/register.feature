@register
Feature: SC-002 - Account Registration Functionality

  As a new visitor,
  I want to be able to create an account,
  So that I can log in and use features for registered users.

  Background:
    Given the user is on the registration page
  (URL: https://ecommerce-playground.lambdatest.io/index.php?route=account/register)

  @positive
  Scenario: TC-006 - Register with all valid data
    When the user fills in First Name with "Wahhab"
    And the user fills in Last Name with "Awaludin"
    And the user fills in Email with "wahhab@lambda.com"
    And the user fills in Telephone with "1234567890"
    And the user fills in Password with "Pass1234" and password confirmation "Pass1234"
    And the user checks the "Privacy Policy" agreement box
    And the user clicks the "Continue" button
    Then the user is successfully registered and redirected to the success page
  (URL: https://ecommerce-playground.lambdatest.io/index.php?route=account/success)

  @negative
  Scenario: TC-007 - Register with an already registered email
    When the user fills the registration form with an existing email "wahhab@lambda.com"
    And the user checks the "Privacy Policy" agreement box
    And the user clicks the "Continue" button
    Then the error message "Warning: E-Mail Address is already registered!" is displayed

  @negative
  Scenario: TC-008 - Register with an empty first name
    When the user leaves the First Name field empty
    And the user fills in the other fields with valid data for a new registration
    And the user checks the "Privacy Policy" agreement box
    And the user clicks the "Continue" button
    Then the error message "First Name must be between 1 and 32 characters!" is displayed

  @negative
  Scenario: TC-009 - Register with mismatching password and password confirmation
    When the user fills in Password with "Pass1234" but password confirmation with "Pass123"
    And the user fills in the other fields with valid data for a new registration
    And the user checks the "Privacy Policy" agreement box
    And the user clicks the "Continue" button
    Then the error message "Password confirmation does not match password!" is displayed

  @negative
  Scenario: TC-010 - Register without agreeing to the Privacy Policy
    When the user fills all registration fields with valid data
    And the user does not check the "Privacy Policy" agreement box
    And the user clicks the "Continue" button
    Then the error message "Warning: You must agree to the Privacy Policy!" is displayed