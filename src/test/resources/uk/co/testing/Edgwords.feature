Feature: Edgewords Shop

  Scenario: Login to edgewords
    Given I am on the edgewords login page
    When I enter valid login information "Ryan.Anderson@2itesting.com" and "TheAlliance322"
    Then I log in to my account


  Scenario: Buying a item with promo code
    Given I am logged in with my edgewords account "Ryan.Anderson@2itesting.com" and "TheAlliance322"
    When I add a item to the basket on the shop page
    And I navigate to the cart
    And I redeem promo code "edgewords"
    Then I get a 10% discount