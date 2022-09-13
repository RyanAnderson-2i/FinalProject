Feature: Edgewords Shop

  Scenario: Login to edgewords
    Given I am on the edgewords login page
    When I enter valid login information
    Then I log in to my account