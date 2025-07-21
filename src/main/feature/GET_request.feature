Feature:Basic Rest Assured Practice

  #/employees{id} - get method to get employee details
  @tag1
  Scenario: Validate GET method in Rest Assured
    Given I create a request "http://dummy.restapiexample.com/api/v1"
    When I send request with body to server "/employees"
    Then I validate response Status Code 200

  @tag1
  Scenario: Validate GET method response parameters
     Given I create a request "http://restapi.demoqa.com/utilities/weather/city"
     When I send request with body to server "/Hyderabad"
     Then I validate response parameters
