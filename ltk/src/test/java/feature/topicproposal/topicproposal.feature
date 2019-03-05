Feature: Topic Proposal Retrieval

  Background:
    * url 'http://localhost:8080'

  Scenario: Retrieve Proposals list

    Given path '/api/proposals'
    When method GET
    Then status 200
    And match $.status == 200
    And match $.message == "success"
    And match each $.result contains { id: '#number' }
    And match $.result[0].topic == "Microservices"


  Scenario: Retrieve specific Proposal

    Given path '/api/proposals/1'
    When method GET
    Then status 200
    And match $.status == 200
    And match $.message == "TopicProposal fetched successfully"
    And match $.result.topic == 'Microservices'


  Scenario: Create Proposal

    Given path '/api/proposals'
    And request
"""
{
   "topic": "okokokok",
   "description": "desc desc",
   "email": "a@b.com",
   "submitted": false,
   "deleted": false
 }
"""
    When method POST
    Then status 201
    And match $.status == 201
    And match $.message == 'TopicProposal created'

