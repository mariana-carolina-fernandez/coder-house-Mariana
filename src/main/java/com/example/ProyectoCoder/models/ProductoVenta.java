package com.example.ProyectoCoder.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "producto_venta")
@Getter
@Setter
public class ProductoVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private VentasEntity venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    @Column
    private Integer count;

    public ProductoVenta(){

    }

    public ProductoVenta(VentasEntity venta, ProductoEntity producto, Integer count) {
        this.venta = venta;
        this.producto = producto;
        this.count = count;
    }

}
