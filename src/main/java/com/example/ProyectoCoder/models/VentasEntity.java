package com.example.ProyectoCoder.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    @Column
    private Float precioFinal;


}
