package com.example.vichenzobackend.service;

import com.example.vichenzobackend.model.MontoMinimo;
import com.example.vichenzobackend.repository.IMontoMinimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MontoMinimoService implements IMontoMinimoService {
    @Autowired
    IMontoMinimoRepository montoMinimoRepository;

    public MontoMinimoService() {
    }

    public Double getMontoMinimo() {
        MontoMinimo montoMinimo = (MontoMinimo)this.montoMinimoRepository.findById(1L).get();
        return montoMinimo.getMontoMinimo();
    }

    public void setMontoMinimo(Double montoMinimo) {
        MontoMinimo montoMin = (MontoMinimo)this.montoMinimoRepository.findById(1L).get();
        montoMin.setMontoMinimo(montoMinimo);
        this.montoMinimoRepository.save(montoMin);
    }
}
