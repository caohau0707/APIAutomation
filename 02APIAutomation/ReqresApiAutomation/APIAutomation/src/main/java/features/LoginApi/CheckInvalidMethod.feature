#Author: Hau
@LoginApi
Feature: Check invalid method

  @InvalidMethod
  Scenario: Check invalid method
    Given I have URL and Method and Reqquest body file name
      | URL                         | Method | RequestBodyName       |
      | https://reqres.in/api/login | GET    | LoginRequestBody.json |
    When I send request with valid url
    Then I check status code and message in response
   
    
