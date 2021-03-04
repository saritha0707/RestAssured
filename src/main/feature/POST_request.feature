Feature:Rest Assured Post Request

Scenario: Validate POST method Status Code
    Given I create a request "https://httpbin.org"
    When I send post request with body to server "/post"
    Then I validate response Status Code 200

  Scenario: Validate POST method Body
    Given I create a request "https://httpbin.org"
    When I send post request with body to server "/post"
    Then I validate post req response body

  Scenario: Validate POST method using params
    Given I create a request "https://httpbin.org"
    When I send post request with body to server "/post" using params
    Then I validate post req response body

   Scenario: Add Employee details to DB
     Given I create a request "http://dummy.restapiexample.com/api/v1"
     When I send post request with body to server "/create" using parameters
     Then I validate post req response

   Scenario: Validate POST method weather parameters
     Given I create a request "http://api.openweathermap.org/data/2.5"
     When I send post request with body to server "/weather" using param
     Then I validate post req response