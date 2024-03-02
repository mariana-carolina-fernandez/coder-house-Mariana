package com.example.ProyectoCoder.models.dto;

import com.example.ProyectoCoder.models.ClientEntity;
import com.example.ProyectoCoder.models.VentasEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter@Setter
public class VentaDto {

    private Long id;
    private Date fecha;
    private ClientEntity cliente;
    private List<ProductoDto> productos;
    private Double totalVenta;

    public VentaDto(VentasEntity venta, List<ProductoDto> productDtos) {
        this.id = venta.getId();
        this.fecha = venta.getFecha();
        this.cliente = venta.getClient();
        this.totalVenta = venta.getPrecioFinal();
        this.productos = productDtos;
    }

    public VentaDto(VentasEntity venta) {
        this.id = venta.getId();
        this.fecha = venta.getFecha();
        this.cliente = venta.getClient();
        this.totalVenta = venta.getPrecioFinal();
        this.productos = new ArrayList<>();
    }

}
