package com.delivery.fee.address.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AddressClient {

    @Bean
    public WebClient webClientAddress(WebClient.Builder builder) {
        return builder
                .baseUrl("viacep.com.br/ws")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.api+json")
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.api+json")
                .build();
    }
}
