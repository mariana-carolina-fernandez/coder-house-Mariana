package com.example.ProyectoCoder.controllers;

import com.example.ProyectoCoder.models.ProductoEntity;
import com.example.ProyectoCoder.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Crea un producto
     * @param product
     * @return
     */
    @PostMapping("agregar/producto")
    public ResponseEntity<?> creatProduct(@RequestBody ProductoEntity product){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(product));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Busca todos los productos de la base de datos
     * @return
     */
    @GetMapping("/productos")
    public ResponseEntity<?> findAllProducts (){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.productos());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Busca un producto por Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> product(@PathVariable long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorId(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Borra un producto por id
     * @param id
     * @return
     */
    @PostMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable long id){
        try{
            Optional<ProductoEntity> product = productoService.buscarPorId(id);
            if(product.isPresent()){
                productoService.borrarProducto(id);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe ese producto en la base de datos");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Modifica el stock del producto en la base de datos
     * @param id
     * @param stock
     * @return
     */
    @PutMapping("/modificarStock/{id}/{stock}")
    @Transactional
    public ResponseEntity<?> modificarStock(@PathVariable Long id, @PathVariable int stock){
        try {
            Optional<ProductoEntity> product = productoService.buscarPorId(id);
            if(product.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(productoService.modificarStock(id,stock));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe ese producto en la base de datos");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Se modifica el precio del produto en la bae de datos
     * @param id
     * @param price
     * @return
     */
    @PutMapping("/modificarPrecio/{id}/{price}")
    @Transactional
    public ResponseEntity<?> modificarPrecio(@PathVariable Long id, @PathVariable Double price){
        try {
            Optional<ProductoEntity> product = productoService.buscarPorId(id);
            if(product.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(productoService.modificarPrecios(id,price));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe ese producto en la base de datos");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    
}
