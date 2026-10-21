Feature: OrangeHRM PIM Module Functionality Add Employee validation
  @Login @smoke @regression @addEmployee
  Scenario: Add multiple employees Validation
    When i navigate to PIM page
    And i add below employees
      | firstName | middleName | lastName |
      | john      | D          | Doe      |
    Then I should see employess added successfully
    And I should validate same employee in Personal details Screen
    When I provide employee personal details in Personal Details Screen
      | dLNumber | licenceExpiryDate | Nationality | Gender |
      | 343L223  | 2029-08-10        | Chinese     | Female |





