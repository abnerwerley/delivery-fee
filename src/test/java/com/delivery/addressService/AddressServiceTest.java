package com.delivery.addressService;

import com.delivery.address.dto.AddressTO;
import com.delivery.address.service.AddressService;
import com.delivery.exception.RequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AddressServiceTest {

    public static final String CEP = "04555-000";
    public static final String CEP_NUMBERS = "04555000";
    public static final String CEP_9 = "123456789";
    public static final String CEP_LETTER = "04555A00";

    @Test
    void testGetAddress() {
        WebClient client = WebClient.builder()
                .baseUrl("viacep.com.br/ws")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.api+json")
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.api+json")
                .build();
        AddressService service = new AddressService(client);
        AddressTO response = service.getAddress(CEP);
        assertNotNull(response);
        assertEquals("SP", response.getUf());
    }

    @Test
    void testGetAddressCepOnlyNumbers() {
        WebClient client = WebClient.builder()
                .baseUrl("viacep.com.br/ws")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.api+json")
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.api+json")
                .build();
        AddressService service = new AddressService(client);
        AddressTO response = service.getAddress(CEP_NUMBERS);
        assertNotNull(response);
        assertEquals("SP", response.getUf());
    }

    @Test
    void testGetAddressCepEmptyString() {
        WebClient client = WebClient.builder()
                .baseUrl("viacep.com.br/ws")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.api+json")
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.api+json")
                .build();
        AddressService service = new AddressService(client);
        Exception exception = Assertions.assertThrows(RequestException.class, () -> service.getAddress(""));
        assertNotNull(exception);
        assertEquals("Please verify if cep has 8 numbers, and numbers only.", exception.getMessage());
    }

    @Test
    void testGetAddressCepNineNumbers() {
        WebClient client = WebClient.builder()
                .baseUrl("viacep.com.br/ws")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.api+json")
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.api+json")
                .build();
        AddressService service = new AddressService(client);
        Exception exception = Assertions.assertThrows(RequestException.class, () -> service.getAddress(CEP_9));
        assertNotNull(exception);
        assertEquals("Please verify if cep has 8 numbers, and numbers only.", exception.getMessage());
    }

    @Test
    void testGetAddressCepWithLetterAmid() {
        WebClient client = WebClient.builder()
                .baseUrl("viacep.com.br/ws")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.api+json")
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.api+json")
                .build();
        AddressService service = new AddressService(client);
        Exception exception = Assertions.assertThrows(RequestException.class, () -> service.getAddress(CEP_LETTER));
        assertNotNull(exception);
        assertEquals("Please verify if cep has 8 numbers, and numbers only.", exception.getMessage());
    }
}
