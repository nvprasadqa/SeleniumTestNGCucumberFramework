Feature: OrangeHRM Dashboard Screen Module Functionality And Verify Application Tabs
  Background:
    Given I am on the login page
    When User enters username as "Admin" and password as "admin123"
    And User clicks on login button
  @tabs
  Scenario: Validate Dashboard screen tabs And click on each tab.
    When User finds all the tabs on dashboard screen
    Then User should be able to validate the tabs count as 12
    And User clicks on each tab
