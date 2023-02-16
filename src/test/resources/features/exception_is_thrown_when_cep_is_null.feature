Feature: Exception is thrown when cep null is sent in request

Scenario: send an invalid cep
    Given null cep
    When request is made with null cep
    Then RequestException is thrown explaining that field cep is mandatory