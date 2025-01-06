package com.example.vichenzobackend.model;


import jakarta.persistence.*;

@Entity
@Table(
        name = "productos_vendidos"
)
public class ProductoVendido {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @ManyToOne(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    private Producto producto;
    private int cantidad;

    public String toString() {
        int var10000 = this.id;
        return "ProductoVendido{id=" + var10000 + ", producto=" + String.valueOf(this.producto) + ", cantidad=" + this.cantidad + "}";
    }

    public ProductoVendido() {
    }

    public ProductoVendido(final int id, final Producto producto, final int cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getId() {
        return this.id;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setProducto(final Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(final int cantidad) {
        this.cantidad = cantidad;
    }
}