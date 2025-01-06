package com.example.vichenzobackend.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "monto_minimo"
)
public class MontoMinimo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "monto_minimo"
    )
    private Double montoMinimo;

    public Long getId() {
        return this.id;
    }

    public Double getMontoMinimo() {
        return this.montoMinimo;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMontoMinimo(final Double montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public MontoMinimo() {
    }

    public MontoMinimo(final Long id, final Double montoMinimo) {
        this.id = id;
        this.montoMinimo = montoMinimo;
    }
}
