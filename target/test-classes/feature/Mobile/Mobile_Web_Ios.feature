@MobileTestWebIOS
#Use below hook to call it, else it will not work : we have introduced it as we don't want hooks to be run during karate test
@BeforeHook
@AfterHook


# Use runner or mvn clean verify to run
# mvn test -Dcucumber.options="--tag @debug1"

Feature: Mobile View


  Scenario: Mobile Test
    Given User OPEN with "<url>"
    Then User PAUSE_IN_SEC with "<waitInSec>"



