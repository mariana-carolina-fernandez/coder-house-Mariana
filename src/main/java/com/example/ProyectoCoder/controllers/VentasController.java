package com.example.ProyectoCoder.controllers;

import com.example.ProyectoCoder.models.VentasEntity;
import com.example.ProyectoCoder.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentasController {

    @Autowired
    private VentasRepository repoVentas;

    @GetMapping("ventas")
    public List<VentasEntity> getVentasEntity(){
        return repoVentas.findAll();
    }
    @PostMapping("compra")
    public String post(@RequestBody VentasEntity ventas){
        repoVentas.save(ventas);
        return "Guardado";
    }
}
