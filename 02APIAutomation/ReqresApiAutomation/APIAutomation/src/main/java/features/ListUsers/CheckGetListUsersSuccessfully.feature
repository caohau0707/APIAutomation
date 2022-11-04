#Author: Hau
@Getlist
Feature: Check get list users successfully

  @GetList
  Scenario: Check get list users successfully
    Given I have URL and method and Request body file name
      | URL                                | Method | RequestBodyName |
      | https://reqres.in/api/users?page=2 | GET    |                 |
    When I send request
    Then I check status code and data in response
