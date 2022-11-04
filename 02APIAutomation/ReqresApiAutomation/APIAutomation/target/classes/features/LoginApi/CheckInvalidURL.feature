#Author: Hau
@LoginApi
Feature: Check invalid URL

  @InvalidURL
  Scenario: Check invalid URL
    Given I have URL and Method and Request body file name
      | URL                             | Method | RequestBodyName       |
      | https://reqres.in/api111/login1 | POST   | LoginRequestBody.json |
    When I send request with invalid url
    Then I validate status code and message
