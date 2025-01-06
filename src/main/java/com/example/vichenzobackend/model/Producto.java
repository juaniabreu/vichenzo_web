package com.example.vichenzobackend.model;


import jakarta.persistence.*;

@Entity
@Table(
        name = "productos"
)
public class Producto {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String nombre;
    private double precio;
    private String imageUrl;
    private String estado = "Activo";

    public Producto(String nombre, double precio, String imageUrl) {
        this.nombre = nombre;
        this.precio = precio;
        this.imageUrl = imageUrl;
    }

    public Producto() {
    }

    public Producto(final Long id, final String nombre, final double precio, final String imageUrl, final String estado) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imageUrl = imageUrl;
        this.estado = estado;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(final double precio) {
        this.precio = precio;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }
}
