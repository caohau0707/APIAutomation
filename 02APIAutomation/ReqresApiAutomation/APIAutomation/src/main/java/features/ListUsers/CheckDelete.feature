#Author: Hau
@Delete
Feature: Check delete successfully

  @Checkdelete
  Scenario: Check delete successfully
    Given I have URL and method and Request body file name
      | URL                           | Method | RequestBodyName |
      | https://reqres.in/api/users/2 | DELETE |                 |
    When I send request
    Then I check status code
