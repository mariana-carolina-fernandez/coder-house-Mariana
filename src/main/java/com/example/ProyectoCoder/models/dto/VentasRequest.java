package com.example.ProyectoCoder.models.dto;

import com.example.ProyectoCoder.models.dto.ProductoRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter@Setter
public class VentasRequest {
    private Long idClient;

    private ArrayList<ProductoRequest> products;
}



