package com.delivery.acceptance.steps;


import com.delivery.address.service.AddressService;
import com.delivery.exception.RequestException;
import com.delivery.fee.controller.FeeController;
import com.delivery.fee.dto.CepForm;
import com.delivery.fee.service.FeeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

public class SendingInValidCepStep {

    public static final String CEP = "A1234567";

    @Given("invalid cep")
    public void invalid_cep() {
        CepForm form = new CepForm(CEP);
    }

    @When("request is made with invalid id")
    public void request_is_made_with_invalid_id() {
        CepForm form = new CepForm(CEP);
        WebClient client = WebClient.builder()
                .baseUrl("viacep.com.br/ws")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.api+json")
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.api+json")
                .build();
        AddressService addressService = new AddressService(client);
        FeeService service = new FeeService(addressService);
        FeeController controller = new FeeController(service);
//        assertNotNull(controller.getFeeByCep(form));
    }

    @Then("RequestException is thrown explaining the error")
    public void requestException_is_thrown_explaining_the_error() {
        CepForm form = new CepForm(CEP);
        WebClient client = WebClient.builder()
                .baseUrl("viacep.com.br/ws")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.api+json")
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.api+json")
                .build();
        AddressService addressService = new AddressService(client);
        FeeService service = new FeeService(addressService);
        FeeController controller = new FeeController(service);
        Exception exception = assertThrows(RequestException.class, () -> controller.getFeeByCep(form));
        assertNotNull(exception);
        assertEquals("Please verify if cep has 8 numbers, and numbers only.", exception.getMessage());
    }
}
