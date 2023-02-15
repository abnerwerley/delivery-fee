package com.delivery.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/receiving_feeResponse_when_a_valid_cep_is_given.feature")
public class DeliveryFeeCucumberRunner {
}
