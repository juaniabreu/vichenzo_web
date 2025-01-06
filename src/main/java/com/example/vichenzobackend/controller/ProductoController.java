package com.example.vichenzobackend.controller;

import com.example.vichenzobackend.model.Producto;
import com.example.vichenzobackend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/productos"})
@CrossOrigin({"*"})
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    public ProductoController() {
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Producto>> getAll() {
        List<Producto> productos = this.productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    @GetMapping({"/getactive"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<Producto>> getAllActive() {
        List<Producto> productos = this.productoService.findAllByEstado("Activo");
        return ResponseEntity.ok(productos);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        Optional<Producto> producto = this.productoService.findById(id);
        return producto.isPresent() ? ResponseEntity.ok((Producto)producto.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping({"/create"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Producto> create(@RequestParam("nombre") String nombre, @RequestParam("precio") Double precio, @RequestParam("imagen") MultipartFile imagen) {
        try {
            Producto producto = this.productoService.save(nombre, precio, imagen);
            return ResponseEntity.ok(producto);
        } catch (IOException var5) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping({"/update/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestParam("nombre") String nombre, @RequestParam("precio") double precio, @RequestParam(value = "imagen",required = false) MultipartFile imagen) {
        try {
            Producto productoActualizado = this.productoService.update(id, nombre, precio, imagen);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception var7) {
            Exception e = var7;
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @PutMapping({"/active/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> activarProducto(@PathVariable Long id) {
        Producto producto = (Producto)this.productoService.findById(id).get();
        producto.setEstado("Activo");
        this.productoService.save(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping({"/delete/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deactivarProducto(@PathVariable Long id) {
        Producto producto = (Producto)this.productoService.findById(id).get();
        producto.setEstado("Inactivo");
        this.productoService.save(producto);
        return ResponseEntity.ok(producto);
    }
}