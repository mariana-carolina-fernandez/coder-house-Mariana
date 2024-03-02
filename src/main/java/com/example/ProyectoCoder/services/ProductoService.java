package com.example.ProyectoCoder.services;

import com.example.ProyectoCoder.models.ProductoEntity;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    ProductoEntity crear(ProductoEntity producto);

    Optional<ProductoEntity> buscarPorId(Long id);

    List<ProductoEntity> productos();

    void borrarProducto(Long id);

    Optional<ProductoEntity> modificarStock(Long id, int stock);

    Optional<ProductoEntity> modificarPrecios(Long id, Double price);

}
