package com.example.ProyectoCoder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="producto")
@Getter
@Setter
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private int stock;

    @Column
    private Double precio;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<VentasEntity> ventasEntityList;

}
