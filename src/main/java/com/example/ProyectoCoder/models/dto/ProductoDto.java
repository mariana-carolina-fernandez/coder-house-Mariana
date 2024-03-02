package com.example.ProyectoCoder.models.dto;

import com.example.ProyectoCoder.models.ProductoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductoDto {

    private Long id;
    private int cantidad;
    private String nombre;
    private Double precio;

    public ProductoDto(ProductoEntity producto, int cantidad) {
        this.id = producto.getId();
        this.cantidad = cantidad;
        this.nombre = producto.getNombre();
        this.precio = producto.getPrecio();
    }

}
