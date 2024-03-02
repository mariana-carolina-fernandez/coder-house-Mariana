package com.example.ProyectoCoder.controllers;

import com.example.ProyectoCoder.models.ClientEntity;
import com.example.ProyectoCoder.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private ClienteService service;

    /**
     * Crea un cliente nuevo
     * @param cliente
     * @return
     */
    @PostMapping("/crear")
    public ResponseEntity<?> createClient(@RequestBody ClientEntity cliente){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.altaCliente(cliente));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Devuelve todos los clientes de la base de datos
     * @return ResponseEntity<?>
     */
    @GetMapping("/todos")
    public ResponseEntity<?> buscarClientes (){
        try{
            List<ClientEntity> clientes = service.obtenerClientes();
            if(!clientes.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(clientes);
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe ningun cliente en la base de datos");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Devuelve un cliente por el id si existe en la base de datos
     * @param id
     * @return ResponseEntity<?>
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> cliente(@PathVariable long id){
        try{
            Optional<ClientEntity> cliente = service.buscarPorId(id);
            if(cliente.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(cliente);
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe el cliente en la base de datos");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Se obtiene una entidad cliente y un id para modificar el cliente si existe en la base de datos
     * @param client
     * @param id
     * @return ResponseEntity<?>
     */
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarCliente(@RequestBody ClientEntity  client, @PathVariable Long id){
        try{

            Optional<ClientEntity> clientEntity = service.buscarPorId(id);
            if(clientEntity.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(service.modificar(client));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe el cliente en la base de datos");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Devuelve la edad del cliente pasando el id
     * @param id
     * @return ResponseEntity<?>
     */
    @GetMapping("/edad/{id}")
    public ResponseEntity<?> obtenerAño(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.obtenerEdad(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    
}
