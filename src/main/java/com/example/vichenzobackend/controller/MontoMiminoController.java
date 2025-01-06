package com.example.vichenzobackend.controller;

import com.example.vichenzobackend.service.MontoMinimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/monto"})
@CrossOrigin({"*"})
public class MontoMiminoController {
    @Autowired
    MontoMinimoService montoMinimoService;

    public MontoMiminoController() {
    }

    @GetMapping
    public ResponseEntity<Double> monto() {
        return new ResponseEntity(this.montoMinimoService.getMontoMinimo(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Double> montoPut(@RequestBody Double monto) {
        this.montoMinimoService.setMontoMinimo(monto);
        return new ResponseEntity(this.montoMinimoService.getMontoMinimo(), HttpStatus.OK);
    }
}