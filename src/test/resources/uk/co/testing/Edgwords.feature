Feature: Edgewords Shop

  Scenario Outline: Buying a item with promo code
    Given I am logged in with my edgewords account "<email>" and "<password>"
    When I add a item to the basket on the shop page
    And I navigate to the cart
    And I redeem promo code "<promocode>"
    Then I get a 10% discount
    Examples:
      | email               | password     | promocode |
      | testEmail@email.com | @Bcd12jgk3sa | edgewords |


  Scenario Outline: Placing and viewing order
    Given I am logged in with my edgewords account "<email>" and "<password>"
    When I add a item to the basket on the shop page
    And I navigate to the cart
    And Place the order
    Then The order numbers should match
    Examples:
      | email               | password     |
      | testEmail@email.com | @Bcd12jgk3sa |
