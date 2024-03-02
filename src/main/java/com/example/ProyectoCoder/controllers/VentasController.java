package com.example.ProyectoCoder.controllers;

import com.example.ProyectoCoder.models.VentasEntity;
import com.example.ProyectoCoder.models.dto.VentaDto;
import com.example.ProyectoCoder.models.dto.VentasRequest;
import com.example.ProyectoCoder.repository.VentasRepository;
import com.example.ProyectoCoder.services.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentasController {

    @Autowired
    private VentasService ventasService;

    @GetMapping("/ventas")
    public List<VentasEntity> getVentasEntity(){
        return (List<VentasEntity>) ventasService.ventas();
    }

    
    @PostMapping("/compra")
    public ResponseEntity<?> post(@RequestBody VentasRequest ventas){
        try {
            VentaDto saleDto = (VentaDto) ventasService.realizarVenta(ventas);
            if(saleDto == null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("No hay stock del producto");
            }
            ventasService.modifySaleProduct(saleDto);
            return ResponseEntity.ok().body(saleDto);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
