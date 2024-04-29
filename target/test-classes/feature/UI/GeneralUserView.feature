@UITest
#Use below hook to call it, else it will not work : we have introduced it as we don't want hooks to be run during karate test
@BeforeHook
@AfterHook


# Use runner or mvn clean verify to run
# mvn test -Dcucumber.options="--tag @debug1"

Feature: General View
#########Different way of writing scenario#################
#  1.Not using OR
  #For this you need to maintain stepDefinitions->Pages(POM)
  #Pages can use in-build functions from common class like click, send text etc.

  Scenario: Validate general user is able to view products without logging in
    Given User navigates to the Online products page
    When User clicks on Formal Shoes drop down
    Then User should be able to view the Products

#  2.Using OR , pls use "<>", so that it will pick locator from OR
  #If you are using this , no need to think about step definitions, mostly they are available, else add it in Commons class under utility package
  #you just need to add objects and values in object,json and use these steps
  Scenario: Validate general user is able to view products without logging in- direct access
    Given User OPEN with "<url>"
    And User PAUSE_IN_SEC with "<waitInSec>"
    And User MAXIMIZE_SCREEN
    When User CLICK on "<hamburger_menu_xpath>"
    And User PAUSE_IN_SEC with "<waitInSec>"
    And User CAPTURE_SCREENSHOT
    Then User VERIFY_TEXT_CONTAINS on "<onlineProductsPage_link_LinkText>" with "<expectedValue>"


