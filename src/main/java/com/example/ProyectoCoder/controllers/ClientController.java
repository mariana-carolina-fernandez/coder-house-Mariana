package com.example.ProyectoCoder.controllers;

import com.example.ProyectoCoder.models.ClientEntity;
import com.example.ProyectoCoder.models.ClientResponse;
import com.example.ProyectoCoder.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public String index(){
        return "Conectado";
    }

    @GetMapping("clientes")
    public List<ClientEntity> getClientEntity(){
        return service.obtenerClientes();
    }
    @PostMapping("alta")
    public ClientEntity post(@RequestBody ClientEntity cliente){
        return service.altaCliente(cliente);
    }

    @GetMapping("edad/{idClient}")
    public ClientResponse obtenerEdad(@PathVariable Long idClient) {
        return service.obtenerEdad(idClient);
    }

    @PostMapping("modificar")
    public String modificar(@RequestBody ClientEntity cliente){
       return service.modificar(cliente);
    }
}
