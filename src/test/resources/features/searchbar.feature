Feature: When the user searches an item in the searchbox then it should give the list of searched item

Background: User has to enter delivery location
  Given the user navigates to the swiggy landing page
  When the user enters the delivery location
  Then the user should be taken to swiggy home page

  Scenario: Verify that the user can able to click on search icon
    When the user clicks on the search icon
    Then the user should be taken to search page
