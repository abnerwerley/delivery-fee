Feature: Exception is thrown when invalid cep is sent

Scenario: send an invalid cep
    Given invalid cep
    When request is made with invalid id
    Then RequestException is thrown explaining the error