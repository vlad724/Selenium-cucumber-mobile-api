@OrangeWebTest
Feature: Test in Orange portal

  Scenario: Test Login Portal
    Given the User fill username text box
    And User fill password text box
    And The User clicks Login button
    Then Verify the user is logged in

@IGNORE
  Scenario: Get System Users from User list
    When the user is logged in
    Then Verify the user is logged in
    Then The user go to system user list
    When Verify Admin user is present in the list

@IGNORE
  Scenario: With an Admin User get System Users from User list
    When the Admin user is Logged in
    And The user go to system user list
    When Verify Admin user is present in the list

