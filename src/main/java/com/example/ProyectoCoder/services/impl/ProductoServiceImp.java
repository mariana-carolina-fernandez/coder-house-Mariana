package com.example.ProyectoCoder.services.impl;

import com.example.ProyectoCoder.models.ProductoEntity;
import com.example.ProyectoCoder.repository.ProductoRepository;
import com.example.ProyectoCoder.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public ProductoEntity crear(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<ProductoEntity> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<ProductoEntity> productos() {
        return productoRepository.findAll();
    }

    @Override
    public void borrarProducto(Long id) {
        Optional<ProductoEntity> producto = productoRepository.findById(id);
        producto.ifPresent(ProductoEntity -> productoRepository.delete(ProductoEntity));
    }

    @Override
    public Optional<ProductoEntity> modificarStock(Long id, int stock) {
        Optional<ProductoEntity> producto = productoRepository.findById(id);
        if(producto.isPresent()){
            ProductoEntity productoDB = producto.get();
            productoDB.setStock(stock);
            return Optional.of(productoRepository.save(productoDB));
        }
        else {
            return Optional.empty() ;
        }    }

    @Override
    public Optional<ProductoEntity> modificarPrecios(Long id, Double precio) {
        Optional<ProductoEntity> producto = productoRepository.findById(id);
        if(producto.isPresent()){
            ProductoEntity productoDB = producto.get();
            productoDB.setPrecio(precio);
            return Optional.of(productoRepository.save(productoDB));

        }else{
            return Optional.empty();
        }    }
}
