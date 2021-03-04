Feature:Authorization Rest Assured Practice with PUT request
## inka complete cheyali
Scenario: Validate Authentication in Rest Assured
  Given I create a request "http://restapi.demoqa.com/authentication/CheckForAuthentication"
  When I send credentials to check the for Authorization
  Then I validate response Status Code 200

Scenario: Validate PUT method in Rest Assured
Given I create a request "http://dummy.restapiexample.com/api/v1"
When I send Authorization PUT request with body "/update/" with employee id 15410
Then I validate response Status Code 200

  Scenario: Validate Authentication in Rest Assured
    Given I create a request
