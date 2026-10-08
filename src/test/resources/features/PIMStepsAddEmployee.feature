Feature: OrangeHRM PIM Module Functionality Add Employee validation
Background:
  Given I am on the login page
  When User enters username as "Admin" and password as "admin123"
  And User clicks on login button
  #Then User should be navigated to dashboard with url contains contains "dashboard"
  @addEmployee
  Scenario: Add multiple employees Validation
    When i navigate to PIM page
    And i add below employees
      | john      | D          | Doe      |  |
    Then I should see employess added successfully

