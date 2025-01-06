package com.example.vichenzobackend.repository;

import com.example.vichenzobackend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findAllByEstado(String estado);
}