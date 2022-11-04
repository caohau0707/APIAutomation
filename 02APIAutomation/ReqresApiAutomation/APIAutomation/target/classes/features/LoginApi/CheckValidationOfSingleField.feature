#Author: hau
@ValidationCase
Feature: Check validation of single field

  @CheckValidation
  Scenario Outline: Check validation of single field
    Given I have login url and method and Request body file name
      | URL                         | Method | RequestBodyName                 |
      | https://reqres.in/api/login | POST   | /LoginApi/LoginRequestBody.json |
    When I send valid login request with "<FieldName>" and "<Value>"
    Then Response return "<StatusCode>" and "<Error Message>"

    Examples: 
      | FieldName | Value              | StatusCode | Error Message             |
      | email     | eve.holt@reqres.in |        200 |                           |
      | email     | missing            |        400 | Missing email or username |
      | email     | null               |        400 | Missing email or username |
      | email     | ""                 |        400 | user not found            |
      | email     | true               |        400 | user not found            |
      | password  | eve.holt@reqres.in |        200 |                           |
      | password  | missing            |        400 | Missing email or username |
      | password  | null               |        400 | Missing email or username |
      | password  | ""                 |        400 | user not found            |
      | password  | true               |        400 | user not found            |
