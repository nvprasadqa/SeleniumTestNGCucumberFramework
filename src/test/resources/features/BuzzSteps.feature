Feature: OrangeHRM PIM Module Functionality Add Employee validation
  Background:
    Given I am on the login page
    When User enters username as "Admin" and password as "admin123"
    And User clicks on login button
  @js
 Scenario: Validate JavaScriptExecutor And Mouse Actions & Keyboard Actions.
   When User Scrolls page using JavaScript executor
   And User click on element using JavaScript executor
   When User performs mouse hover on question mark icon
   And User post a commnent and click on post
#   Then Help Message should be displayed


