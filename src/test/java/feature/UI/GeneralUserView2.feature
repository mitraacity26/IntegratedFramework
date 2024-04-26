@UITest
#Use below hook to call it, else it will not work: we have introduced it as we don't want hooks to be run during karate test
@BeforeHook
@AfterHook

Feature: Test

  Scenario: Test
    Given User OPEN with "https://chat.openai.com/"