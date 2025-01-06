package com.example.vichenzobackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.vichenzobackend.model.Producto;
import com.example.vichenzobackend.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    IProductoRepository productoRepository;
    @Autowired
    private Cloudinary cloudinary;

    public ProductoService() {
    }

    public List<Producto> findAll() {
        return this.productoRepository.findAll();
    }

    public List<Producto> findAllByEstado(String estado) {
        return this.productoRepository.findAllByEstado(estado);
    }

    public Optional<Producto> findById(Long id) {
        return this.productoRepository.findById(id);
    }

    public Producto save(String nombre, double precio, MultipartFile imagen) throws IOException {
        Map<String, Object> uploadResult = this.cloudinary.uploader().upload(imagen.getBytes(), ObjectUtils.emptyMap());
        String imagenUrl = (String)uploadResult.get("url");
        Producto producto = new Producto(nombre, precio, imagenUrl);
        return (Producto)this.productoRepository.save(producto);
    }

    public void delete(Producto producto) {
        this.productoRepository.delete(producto);
    }

    public Producto update(Long id, String nombre, double precio, MultipartFile imagen) throws IOException {
        Producto producto = (Producto)this.productoRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Producto no encontrado");
        });
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        if (imagen != null && !imagen.isEmpty()) {
            Map<String, Object> uploadResult = this.cloudinary.uploader().upload(imagen.getBytes(), ObjectUtils.emptyMap());
            String imagenUrl = (String)uploadResult.get("url");
            producto.setImageUrl(imagenUrl);
        }

        return (Producto)this.productoRepository.save(producto);
    }

    public void save(Producto p) {
        this.productoRepository.save(p);
    }
}
