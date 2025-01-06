package com.example.vichenzobackend.service;

import com.example.vichenzobackend.model.ProductoVendido;
import com.example.vichenzobackend.repository.IProductoVendidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducoVendidoService implements IProductoVendidoService {
    @Autowired
    IProductoVendidoRepository productoVendidoRepository;

    public ProducoVendidoService() {
    }

    public List<ProductoVendido> findAll() {
        return this.productoVendidoRepository.findAll();
    }

    public Optional<ProductoVendido> findById(Long id) {
        return this.productoVendidoRepository.findById(id);
    }

    public ProductoVendido save(ProductoVendido productoVendido) {
        return (ProductoVendido)this.productoVendidoRepository.save(productoVendido);
    }

    public void delete(ProductoVendido productoVendido) {
        this.productoVendidoRepository.delete(productoVendido);
    }

    public ProductoVendido update(ProductoVendido productoVendido) {
        return (ProductoVendido)this.productoVendidoRepository.save(productoVendido);
    }
}
