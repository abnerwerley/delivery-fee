package com.delivery.acceptance.steps;


import com.delivery.address.service.AddressService;
import com.delivery.fee.controller.FeeController;
import com.delivery.fee.dto.CepForm;
import com.delivery.fee.dto.FeeResponse;
import com.delivery.fee.service.FeeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendingValidCepStep {

    public static final String CEP = "04851280";

    @Given("valid cep")
    public void valid_cep() {
        CepForm form = new CepForm(CEP);
    }

    @When("request is made")
    public void request_is_made() {
        CepForm form = new CepForm(CEP);
        RestTemplate restTemplate = new RestTemplate();
        AddressService addressService = new AddressService(restTemplate);
        FeeService service = new FeeService(addressService);
        FeeController controller = new FeeController(service);
        Assertions.assertNotNull(controller.getFeeByCep(form));
    }

    @Then("FeeResponse is returned according to cep")
    public void feeResponse_is_returned_according_to_cep() {
        CepForm form = new CepForm(CEP);
        RestTemplate restTemplate = new RestTemplate();
        AddressService addressService = new AddressService(restTemplate);
        FeeService service = new FeeService(addressService);
        FeeController controller = new FeeController(service);
        FeeResponse response = controller.getFeeByCep(form);
        Assertions.assertNotNull(response);
        assertEquals(new BigDecimal("7.85"), response.getFrete());
    }
}
