package com.example.vichenzobackend.controller;

import com.example.vichenzobackend.model.Producto;
import com.example.vichenzobackend.model.ProductoVendido;
import com.example.vichenzobackend.model.Venta;
import com.example.vichenzobackend.service.ProductoService;
import com.example.vichenzobackend.service.SendEmailService;
import com.example.vichenzobackend.service.UserService;
import com.example.vichenzobackend.service.VentaService;
import com.resend.core.exception.ResendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping({"/api/ventas"})
@CrossOrigin({"*"})
public class VentaController {
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private VentaService ventaService;
    @Autowired
    private UserService userService;

    public VentaController() {
    }

    @GetMapping({"/getall"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Venta>> getAll() {
        List<Venta> ventas = this.ventaService.findAll();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Venta> getById(@PathVariable Long id) {
        Optional<Venta> venta = this.ventaService.findById(id);
        return venta.isPresent() ? ResponseEntity.ok((Venta)venta.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Venta> create(@RequestBody Venta venta) throws ResendException {
        Venta v = new Venta();
        v.setCuit(venta.getCuit());
        v.setRazonSocial(venta.getRazonSocial());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
        Date fecha = new Date();
        String formattedDate = formatter.format(fecha);
        v.setFecha(formattedDate);
        v.setTipoEnvio(venta.getTipoEnvio());
        if (venta.getTipoEnvio().toLowerCase() == "local") {
            v.setDomicilio("");
        } else {
            v.setDomicilio(venta.getDomicilio());
        }

        v.setTelefono(venta.getTelefono());
        v.setFormaPago(venta.getFormaPago());
        v.setEstado(venta.getEstado());
        v.setTotal(venta.getTotal());
        v.setEmail(venta.getEmail());
        List<ProductoVendido> vendidos = new ArrayList();
        Iterator var7 = venta.getProductos().iterator();

        while(var7.hasNext()) {
            ProductoVendido p = (ProductoVendido)var7.next();
            Long productoId = p.getProducto().getId();
            Producto producto = (Producto)this.productoService.findById(productoId).orElseThrow(() -> {
                return new NoSuchElementException("Producto no encontrado" + productoId);
            });
            ProductoVendido pv = new ProductoVendido();
            pv.setProducto(producto);
            pv.setCantidad(p.getCantidad());
            vendidos.add(pv);
        }

        v.setProductos(vendidos);
        String body = venta.generarResumenVenta(vendidos);
        this.sendEmailService.sendEmail(v.getEmail(), body);
        this.ventaService.save(v);
        return ResponseEntity.ok(v);
    }

    @PutMapping({"/update/{id}"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Venta> update(@PathVariable Long id, @RequestBody String estado) {
        Venta v = (Venta)this.ventaService.findById(id).get();
        v.setEstado(estado.replace("\"", ""));
        this.ventaService.save(v);
        return ResponseEntity.ok(v);
    }
}