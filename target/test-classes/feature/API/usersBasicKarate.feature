@testPOSTFeature
Feature: sample karate test script - Basic E2E Scenario

  Background: 
    * url baseURL
    * configure ssl = true
    #Access token
#    * def token = callonce read('classpath:feature/usersToken.feature')
#    * def newAuthToken = "Bearer " + token.tokenGenerated
#    * print newAuthToken

  @testPOST
  Scenario: Create a New Person
    Given path '/api/users'
#    And header Authorization = newAuthToken
    And request
      """
      {
        "name": "morpheus",
        "job": "leader"
      }
      """
    
    When method POST
    Then status 201
    * print response
    * def id = response.id
    * assert response.name ==  'morpheus'

