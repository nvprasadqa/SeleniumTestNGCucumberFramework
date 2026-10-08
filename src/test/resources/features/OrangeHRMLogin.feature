Feature: OrangeHRM Login Functionality validation


@smoke @positive
Scenario: Successful login with valid credentials
  Given I am on the login page
  When User enters username as "Admin" and password as "admin123"
  And User clicks on login button
  Then User should be navigated to dashboard with url contains contains "dashboard"

@smoke @negative
Scenario Outline: Login failure with invalid credentials with error validation
  Given I am on the login page
  When User enters username as "<username>" and password as "<password>"
  And User clicks on login button
  Then User should see an error message as "<errorMessage>"
#  Then User should see an error message

  Examples:
    | username | password   | errorMessage        |  |
    | Admin    | sdlkfjsd   | Invalid credentials |  |
    | test     | admin123   | Invalid credentials |  |
    | selenium | automaiton | Invalid credentials |  |





