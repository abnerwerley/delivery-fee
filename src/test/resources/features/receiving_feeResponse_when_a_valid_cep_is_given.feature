Feature: Receiving feeResponse when a valid cep is given

Scenario: send a valid cep
    Given valid cep
    When request is made
    Then FeeResponse is returned according to cep