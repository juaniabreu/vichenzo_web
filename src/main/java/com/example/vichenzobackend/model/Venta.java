package com.example.vichenzobackend.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(
        name = "ventas"
)
public class Venta {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private String razonSocial;
    private String cuit;
    private double total;
    private String telefono;
    private int cantidad;
    @ManyToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "venta_producto",
            joinColumns = {@JoinColumn(
                    name = "venta_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "producto_id"
            )}
    )
    private List<ProductoVendido> productos = new ArrayList();
    private String tipoEnvio;
    private String formaPago;
    private String fecha;
    private String domicilio;
    private String email;
    private String estado = "Pendiente";

    public String generarResumenVenta(List<ProductoVendido> productos) {
        StringBuilder html = new StringBuilder();
        html.append("<h1>Gracias por tu compra</h<1>");
        html.append("<p>Este es el resumen de tu compra:</p>");
        html.append("<table border='1' style='border-collapse: collapse;'>");
        html.append("<tr><th>Producto</th><th>Precio</th><th>Cantidad</th></tr>");
        double total = 0.0;

        ProductoVendido producto;
        for(Iterator var5 = productos.iterator(); var5.hasNext(); total += producto.getProducto().getPrecio() * (double)producto.getCantidad()) {
            producto = (ProductoVendido)var5.next();
            html.append("<tr>").append("<td>").append(producto.getProducto().getNombre()).append("</td>").append("<td>$").append(producto.getProducto().getPrecio()).append("</td>").append("<td>").append(producto.getCantidad()).append("</td>").append("</tr>");
        }

        html.append("</table>");
        html.append("<p>Total: $").append(String.format("%.2f", total)).append("</p>");
        return html.toString();
    }

    public long getId() {
        return this.id;
    }

    public String getRazonSocial() {
        return this.razonSocial;
    }

    public String getCuit() {
        return this.cuit;
    }

    public double getTotal() {
        return this.total;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public List<ProductoVendido> getProductos() {
        return this.productos;
    }

    public String getTipoEnvio() {
        return this.tipoEnvio;
    }

    public String getFormaPago() {
        return this.formaPago;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getDomicilio() {
        return this.domicilio;
    }

    public String getEmail() {
        return this.email;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setRazonSocial(final String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setCuit(final String cuit) {
        this.cuit = cuit;
    }

    public void setTotal(final double total) {
        this.total = total;
    }

    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

    public void setCantidad(final int cantidad) {
        this.cantidad = cantidad;
    }

    public void setProductos(final List<ProductoVendido> productos) {
        this.productos = productos;
    }

    public void setTipoEnvio(final String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public void setFormaPago(final String formaPago) {
        this.formaPago = formaPago;
    }

    public void setFecha(final String fecha) {
        this.fecha = fecha;
    }

    public void setDomicilio(final String domicilio) {
        this.domicilio = domicilio;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public Venta(final long id, final String razonSocial, final String cuit, final double total, final String telefono, final int cantidad, final List<ProductoVendido> productos, final String tipoEnvio, final String formaPago, final String fecha, final String domicilio, final String email, final String estado) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.total = total;
        this.telefono = telefono;
        this.cantidad = cantidad;
        this.productos = productos;
        this.tipoEnvio = tipoEnvio;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.domicilio = domicilio;
        this.email = email;
        this.estado = estado;
    }

    public Venta() {
    }
}
