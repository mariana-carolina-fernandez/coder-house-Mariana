package com.example.ProyectoCoder.services;

import com.example.ProyectoCoder.models.dto.VentasRequest;
import com.example.ProyectoCoder.models.dto.VentaDto;

public interface VentasService {

    Object realizarVenta(VentasRequest sale);

    Object ventas();

    void modifySaleProduct(VentaDto ventaDto);
}
