package com.delivery.fee.address.service;

import com.delivery.fee.address.dto.AddressTO;
import com.delivery.fee.exception.RequestException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Setter
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new RequestException("Could not find Address by cep : " + cep);
        }
    }
}
