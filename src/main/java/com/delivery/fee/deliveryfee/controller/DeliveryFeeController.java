package com.delivery.fee.deliveryfee.controller;

import com.delivery.fee.deliveryfee.dto.CepForm;
import com.delivery.fee.deliveryfee.dto.FeeResponse;
import com.delivery.fee.deliveryfee.service.DeliveryFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class DeliveryFeeController {

    @Autowired
    private DeliveryFeeService service;

    @PostMapping("/consulta-endereco")
    @ResponseStatus(HttpStatus.OK)
    FeeResponse getFeeByCep(@RequestBody CepForm form) {
        return service.generateResponse(form.getCep());
    }
}
