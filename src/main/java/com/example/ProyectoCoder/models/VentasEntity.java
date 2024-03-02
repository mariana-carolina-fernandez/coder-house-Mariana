package com.example.ProyectoCoder.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="ventas")
@Getter
@Setter
public class VentasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cliente;

    @Column
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "producto_venta", joinColumns = @JoinColumn(name = "venta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    @JsonManagedReference
    private List<ProductoEntity> products;

    @Column
    private Double precioFinal;


}
