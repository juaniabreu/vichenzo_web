package com.example.vichenzobackend.repository;


import com.example.vichenzobackend.model.ProductoVendido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoVendidoRepository extends JpaRepository<ProductoVendido, Long> {
}

