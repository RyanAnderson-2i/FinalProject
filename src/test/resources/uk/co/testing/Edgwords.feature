Feature: Edgewords Shop

  Scenario: Login to edgewords
    Given I am on the edgewords login page
    When I enter valid login information "Ryan.Anderson@2itesting.com" and "TheAlliance322"
    Then I log in to my account