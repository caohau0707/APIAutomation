#Author: hau
@LoginApi
Feature: Check response when send request successfully

  @MainCase
  Scenario: Check response when send request successfully
    Given I have URL and method and Request body file name
      | URL                         | Method | RequestBodyName                 |
      | https://reqres.in/api/login | POST   | /LoginApi/LoginRequestBody.json |
    When I send request
    Then I validate status code and token
