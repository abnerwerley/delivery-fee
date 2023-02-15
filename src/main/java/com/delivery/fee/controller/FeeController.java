package com.delivery.fee.controller;


import com.delivery.fee.dto.CepForm;
import com.delivery.fee.dto.FeeResponse;
import com.delivery.fee.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class FeeController {

    @Autowired
    private FeeService service;

    @PostMapping("/consulta-endereco")
    @ResponseStatus(HttpStatus.OK)
    FeeResponse getFeeByCep(@RequestBody CepForm form) {
        return service.generateResponse(form.getCep());
    }
}

