package com.example.ProyectoCoder.services;

import com.example.ProyectoCoder.models.ClientEntity;
import com.example.ProyectoCoder.models.ClientResponse;
import com.example.ProyectoCoder.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    //Desafio Cliente API Rest
    //Funcion que devuelve una clase con el nombre, apellido y edad
    public ClientResponse obtenerEdad(@PathVariable Long idClient) {
        ClientResponse response = new ClientResponse();
        int edadEnAnios = 0;
        Optional<ClientEntity> cliente = repo.findById(idClient);
        LocalDate fechaActual = LocalDate.now();
        if (cliente.isPresent()) {
            LocalDate fechaNacimiento = cliente.get().getFechaNacimiento().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            Period periodo = Period.between(fechaNacimiento, fechaActual);
            edadEnAnios = periodo.getYears();
            response.setNombre(cliente.get().getNombre());
            response.setApellido(cliente.get().getApellido());
            response.setEdad(edadEnAnios);
        }
        return response;

    }

    public ClientEntity altaCliente (ClientEntity cliente) {
        return repo.save(cliente);
    }
    public List<ClientEntity> obtenerClientes(){
        return  repo.findAll();
    }
    public String modificar (@RequestBody ClientEntity cliente){
        Optional<ClientEntity> clienteRepo = repo.obtenerPorEmail(cliente.getEmail());
        if (clienteRepo.isPresent()){
            ClientEntity clienteBD = clienteRepo.get();
            clienteBD.setNombre(cliente.getNombre());
            clienteBD.setFechaNacimiento(cliente.getFechaNacimiento());
            clienteBD.setApellido(cliente.getApellido());
            repo.save(clienteBD);
            return "Guardado";
        }
        return "No se encontró usuario";
    }
    }

