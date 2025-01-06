package com.example.vichenzobackend.repository;


import com.example.vichenzobackend.model.MontoMinimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMontoMinimoRepository extends JpaRepository<MontoMinimo, Long> {
}
