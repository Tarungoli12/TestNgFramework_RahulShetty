Feature: Purchasing the order from shopping website

  Background:
    Given I opened Shopping Website

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given logged with username "<username>" and password "<password>"
    When I add product "<product>" to cart
    And Checkout "<product>" and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage
    Examples:
      | username          | password | product |
      | demo845@gmail.com | Demo@845 | ZARA    |


