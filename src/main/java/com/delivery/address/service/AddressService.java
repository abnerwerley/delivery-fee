package com.delivery.address.service;

import com.delivery.address.dto.AddressTO;
import com.delivery.exception.RequestException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AddressService {

    @Autowired
    private WebClient webClient;

    public AddressTO getAddress(String cep) {
        try {
            Mono<AddressTO> monoAddress = webClient.method(HttpMethod.GET)
                    .uri("/{cep}/json", cep)
                    .retrieve()
                    .bodyToMono(AddressTO.class);

            return monoAddress.block();

        } catch (WebClientResponseException e) {
            log.error("Please verify if cep has 8 numbers, and numbers only.");
            throw new RequestException("Please verify if cep has 8 numbers, and numbers only.");

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RequestException("Could not find Address by cep : " + cep);
        }
    }
}