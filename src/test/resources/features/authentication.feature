Feature: Authentication

  @authentication
  Scenario: Successfully authenticate with valid credentials
    Given the user has valid authentication credentials
    When the user sends an authentication request
    Then the authentication request should be successful
    And an authentication token should be returned

  @authentication @negative
  Scenario Outline: Authenticate with different credentials
    Given the user has authentication credentials "<username>" and "<password>"
    When the user sends an authentication request
    Then the authentication response should match "<expected>"

  Examples:
    | username | password      | expected |
    | admin    | password123   | success  |
    | admin    | wrongPassword | failure  |