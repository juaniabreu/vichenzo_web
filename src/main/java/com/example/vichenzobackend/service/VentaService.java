package com.example.vichenzobackend.service;

import com.example.vichenzobackend.model.Venta;
import com.example.vichenzobackend.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService {
    @Autowired
    IVentaRepository ventaRepository;

    public VentaService() {
    }

    public List<Venta> findAll() {
        return this.ventaRepository.findAll();
    }

    public Optional<Venta> findById(Long id) {
        return this.ventaRepository.findById(id);
    }

    public Venta save(Venta venta) {
        return (Venta)this.ventaRepository.save(venta);
    }

    public void delete(Venta venta) {
        this.ventaRepository.delete(venta);
    }

    public Venta update(Venta venta) {
        return (Venta)this.ventaRepository.save(venta);
    }
}
