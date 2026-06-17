Feature: Login Error Validation

  @smoke
  Scenario: Error Validation
    Given I opened Shopping Website
    Given logged with username "demo845@gmail.com" and password "Demo@84"
    Then "Incorrect email or password." message is displayed