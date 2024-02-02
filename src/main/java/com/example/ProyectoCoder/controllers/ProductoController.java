package com.example.ProyectoCoder.controllers;

import com.example.ProyectoCoder.models.ProductoEntity;
import com.example.ProyectoCoder.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository repoProducto;

    @GetMapping("producto")
    public List<ProductoEntity> getProductoEntity(){
        return repoProducto.findAll();
    }
    @PostMapping("agregar/producto")
    public String post(@RequestBody ProductoEntity producto){
        repoProducto.save(producto);
        return "Guardado";
    }
}
