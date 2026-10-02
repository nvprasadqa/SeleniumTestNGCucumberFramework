Feature: OrangeHRM Login Functionality validation


@smoke @positive
Scenario: Successful login with valid credentials
  Given I am on the login page
  When User enters username as "Admin" and password as "admin123"
  And User clicks on login button
  #Then User should be navigated to dashbaord with url contains contains "dashboard"

#@smoke @negative
#Scenario Outline: Login failure with invalid credentials with error validation
#  When User enters username as "<username>" and password as "<password>"
#  And User clicks on login button
#  Then User should see an error message as "Invalid credentials"
#
#  Examples:
#    | username | password |
#    | Admin    | sdlkfjsd |
#    | test     | admin123 |
#    | selenium | automaiton |





