Feature: Authentication
  Background:
    Given I have opened the application

  @wip
  Scenario: Attempt to log in with wrong password

    Given a user exists with email "graham@grahamcox.co.uk" and password "password"
    When I try to log in as email "graham@grahamcox.co.uk" and password "wrong"
    Then I get the log in error "The password you entered was incorrect"