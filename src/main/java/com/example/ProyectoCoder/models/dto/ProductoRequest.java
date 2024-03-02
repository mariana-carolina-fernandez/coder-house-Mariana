package com.example.ProyectoCoder.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductoRequest {
    private Long idSale;

    private Long idProduct;

    private int count;
}
