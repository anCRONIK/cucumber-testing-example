Feature: Application health checking

  Scenario: Checking application health with dependencies statuses
    When the client calls /actuator/health with http method GET
    Then the client receives status code of 200
    And the client receives dependencies health statuses
      | dependency_name | status |
      | db              | UP     |
      | ping            | UP     |