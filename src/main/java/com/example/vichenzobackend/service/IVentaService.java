package com.example.vichenzobackend.service;

import com.example.vichenzobackend.model.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {
    List<Venta> findAll();

    Optional<Venta> findById(Long id);

    Venta save(Venta venta);

    void delete(Venta venta);

    Venta update(Venta venta);
}
