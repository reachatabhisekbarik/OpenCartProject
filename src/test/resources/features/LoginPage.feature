Feature: Login Functionality for OpenCart E-commerce Website

  As a user of the OpenCart website
  I want to be able to log in with my account
  So that I can access my account-related features and manage my orders

  Background:
    Given I am on the OpenCart login page

  Scenario: Successful login with valid credentials
    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully

  Scenario Outline: Unsuccessful login with invalid or empty credentials
    Given I have entered invalid "<username>" and "<password>"
    When I click on the login button
    Then I should see an error message indicating "<error_message>"

    Examples:
      | username              | password          | error_message                                         |
      | invalid2222@gmail.com | invalid22Password | Warning: No match for E-Mail Address and/or Password. |
      | abccc22c              | valid22Password   | Warning: No match for E-Mail Address and/or Password. |
      | valid12@gmail.com     | abccc             | Warning: No match for E-Mail Address and/or Password. |

  Scenario: Navigating to the forgotten password page
    When I click on the Forgotten Password link
    Then I should be redirected to the password reset page