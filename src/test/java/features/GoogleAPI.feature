Feature: Validate Google API (Rahul Shetty Acacdemy example)

  Scenario: Validate Add Place API
    Given User has Payload to POST
    When User make a "POST" call to the "/maps/api/place/add/json"
    Then Validate the "status" response is "OK"
    Then Validate the "scope" response is "APP"

#    Below is example for Data Driven Mechanism
  Scenario Outline:Validate Add Place API
    Given User has Payload to POST with "<Name>", "<Language>" and "<Address>"
    When User make a "POST" call to the "/maps/api/place/add/json"
    Then Validate the "status" response is "OK"
    Then Validate the "scope" response is "APP"
    Then get the "place_id" from response

    @AddPlace
    Examples:
    |  Name           | Language | Address      |
    | Frontline house | Telugu   | 123 Test Dr  |

  Scenario Outline: Delete place
    Given User has Payload to Delete
    When User make a "POST" call to the "DeletePlaceAPI" using enum class
    Then Validate the "status" response is "OK"

    @DeletePlace
    Examples:
      |  Name           | Language | Address      |
      | Frontline house | Telugu   | 123 Test Dr  |

#    Testing E2E scenario using enum class to pull API details from src/test/java/resources/APIdetails.java

  Scenario Outline: Add Place, Get Place details and Delete place
    Given User has Payload to POST with "<Name>", "<Language>" and "<Address>"
    When User make a "POST" call to the "AddPlaceAPI" using enum class
    Then Validate the "status" response is "OK"
    Then get the "place_id" from response
    When User make a "GET" call to the "GetPlaceAPI" using enum class
    Then Validate the "name" response is "<Name>"
    And User has Payload to Delete
    When User make a "POST" call to the "DeletePlaceAPI" using enum class
    Then Validate the "status" response is "OK"

    @E2E
    Examples:
      |  Name           | Language | Address      |
      | Frontline house | Telugu   | 123 Test Dr  |