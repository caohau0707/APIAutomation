#Author: Hau
@Delete
Feature: Check put successfully

  @Checkdelete
  Scenario: Check put successfully
    Given I have URL and method and Request body file name
      | URL                           | Method | RequestBodyName     |
      | https://reqres.in/api/users/2 | PUT    | /CreateUserApi/PutRequestBody.json|
    When I send request
    Then I check status code after put
