#Author: hau
@CreateUserApi
Feature: Create user successfully

  @MainCase
  Scenario: Create user successfully
    Given I have URL and method and Request body file name
      | URL                         | Method | RequestBodyName                      |
      | https://reqres.in/api/users | POST   | /CreateUserApi/LoginRequestBody.json |
    When I send request
    Then I validate status code and name and job
