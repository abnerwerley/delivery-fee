package com.delivery.fee.deliveryfee.service;

import com.delivery.fee.address.dto.AddressTO;
import com.delivery.fee.address.service.AddressService;
import com.delivery.fee.deliveryfee.dto.EnumBrazilianZones;
import com.delivery.fee.deliveryfee.dto.FeeResponse;
import com.delivery.fee.exception.RequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class DeliveryFeeService {

    @Autowired
    private AddressService addressService;

    public FeeResponse generateResponse(String cep) {
        try {
            AddressTO address = addressService.getAddress(cep);
            verifyZone(address.getUf());
            return new FeeResponse(address, getFeeByZone(address.getUf()));
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
