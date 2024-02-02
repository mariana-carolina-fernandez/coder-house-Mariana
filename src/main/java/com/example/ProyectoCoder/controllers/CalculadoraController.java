package com.example.ProyectoCoder.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {
    @GetMapping("suma/{num1}/{num2}")
    public Float suma(@PathVariable Float num1,@PathVariable  Float num2){
        Float resultado = num1 + num2;
        return resultado;

    }
    @GetMapping("resta/{num1}/{num2}")
    public Float resta(@PathVariable Float num1,@PathVariable Float num2){
        Float resultado = num1 - num2;
        return resultado;
    }
    @GetMapping("multiplicacion/{num1}/{num2}")
    public Float multiplicacion(@PathVariable Float num1,@PathVariable Float num2){
        Float resultado= num1*num2;
        return resultado;
    }
    @GetMapping("division/{num1}/{num2}")
    public ResponseEntity<?> division(@PathVariable Float num1,@PathVariable Float num2){
        if (num2!=0) {
            Float resultado = num1 / num2;
            return ResponseEntity.ok(resultado) ;
        } else {
         return (ResponseEntity<?>) ResponseEntity.ok();
        }
    }


}
