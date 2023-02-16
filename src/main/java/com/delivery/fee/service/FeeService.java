package com.delivery.fee.service;

import com.delivery.address.dto.AddressTO;
import com.delivery.address.service.AddressService;
import com.delivery.exception.RequestException;
import com.delivery.fee.dto.EnumBrazilianZones;
import com.delivery.fee.dto.FeeResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class FeeService {

    @Autowired
    private AddressService addressService;

    public FeeResponse generateResponse(String cep) {
        try {
            if (cep != null) {
                AddressTO address = addressService.getAddressTemplate(cep);
                verifyZone(address.getUf());
                return new FeeResponse(address, getFeeByZone(address.getUf()));
            }
            throw new RequestException("Cep is mandatory.");
        } catch (RequestException e) {
            log.error(e.getMessage());
            throw new RequestException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RequestException("Could not calculate delivery fee for cep: " + cep);
        }
    }

    public String verifyZone(String state) {
        for (EnumBrazilianZones zone : EnumBrazilianZones.values()) {
            if (zone.hasState(state)) {
                return zone.name();
            }
        }
        throw new NoSuchElementException("State isn't from Brazil.");
    }

    public BigDecimal getFeeByZone(String state) {
        for (EnumBrazilianZones zone : EnumBrazilianZones.values()) {
            if (zone.hasState(state)) {
                return zone.getFee();
            }
        }
        throw new NoSuchElementException("State isn't from Brazil.");
    }
}
