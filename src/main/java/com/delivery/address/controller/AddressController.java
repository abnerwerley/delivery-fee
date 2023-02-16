package com.delivery.address.controller;

import com.delivery.address.dto.AddressTO;
import com.delivery.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping("")
    public AddressTO getAddressTemplate(@RequestParam String cep) {
        return service.getAddressTemplate(cep);
    }
}
