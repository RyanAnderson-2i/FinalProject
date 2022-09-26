Feature: Edgewords Shop

  Scenario: Buying a item with promo code
    Given I am logged in with my edgewords account "Ryan.Anderson@2itesting.com" and "TheAlliance322"
    When I add a item to the basket on the shop page
    And I navigate to the cart
    And I redeem promo code "edgewords"
    Then I get a 10% discount

    Scenario: Placing and viewing order
      Given I am logged in with my edgewords account "Ryan.Anderson@2itesting.com" and "TheAlliance322"
      When I add a item to the basket on the shop page
      And I navigate to the cart
      And Place the order
      Then The order numbers should match