Feature: When the user searches an item in the searchbox then it should give the list of searched item

Background: User has to enter delivery location
  Given the user navigates to the swiggy landing page
  When the user enters the delivery location
  Then the user should be taken to swiggy home page

  Scenario: Verify that the user can able to click on search icon
    When the user clicks on the search icon
    Then the user should be taken to search page

  Scenario: Verify that when the user clicks on the search icon then the search box is displayed
    When the user clicks on the search
    Then the search box should be displayed

  Scenario: Verify that the dimension of the search box is as per requirement
    When the user clicks on the search
    Then the search box should be displayed as per requirement for the dimension

  Scenario: Verify that the placeholder is present inside the search box
    When the user clicks on the search
    Then the search box should be displayed with placeholder inside it

  Scenario:Verify that the user is able to enter an item in the search box
    When the user clicks on the search
    And the user enters an item inside the search box
    Then the user should be able to enter an item inside the search box

  Scenario: Verify that when the user enters special character in the search box
    When the user clicks on the search
    And the user enters a special character
    And the user clicks on see for all results
    Then the user should get an error message saying no match found

  Scenario: Verify that the user is able to navigate back inside the search box
    When the user clicks on the search
    And the user enters an item
    And the user clicks on the navigate back present inside the search box
    Then the user should be able to navigate back

  Scenario: Verify that the user is able to clear the text
    When the user clicks on the search
    And the user enters an item inside the search box
    And the user clicks on the cross icon present inside the search box
    Then the user should be able to clear the text

  Scenario: Verify that when the user enters an item name in the search box an auto suggestion is shown
    When the user clicks on the search
    And the user enters an item name inside the search box
    Then the auto suggestion must be shown below the search box

  Scenario Outline: Verify that when user enters an item name in the search box should give list of matching items
    When the user clicks on the search
    And the user enters an item name as "<itemname>" inside the search box
    Then the user should get list of items matching the entered items below the search box
    Examples:
      |itemname|
      |panner 65|
      |Bi       |
      |65       |

  Scenario: Verify that when the user enters single character then the auto suggestion is shown
    When the user clicks on the search
    And the user enters a single character inside the search box
    Then the user should get an auto suggestion matching the items based on the entered character