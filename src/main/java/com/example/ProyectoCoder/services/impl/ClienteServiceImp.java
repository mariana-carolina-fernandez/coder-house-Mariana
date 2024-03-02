package com.example.ProyectoCoder.services.impl;

import com.example.ProyectoCoder.models.ClientEntity;
import com.example.ProyectoCoder.models.dto.ClientResponse;
import com.example.ProyectoCoder.repository.ClientRepository;
import com.example.ProyectoCoder.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ClientRepository clientRepository;

    //Desafio Cliente API Rest
    //Funcion que devuelve una clase con el nombre, apellido y edad
    @Override

    public ClientResponse obtenerEdad(@PathVariable Long idClient) {
        ClientResponse response = new ClientResponse();
        int edadEnAnios = 0;
        Optional<ClientEntity> cliente = clientRepository.findById(idClient);
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
    @Override

    public ClientEntity altaCliente (ClientEntity cliente) {
        return clientRepository.save(cliente);
    }

    @Override
    public Optional<ClientEntity> buscarPorId(long id) {
        return clientRepository.findById(id);
    }
    @Override
    public List<ClientEntity> obtenerClientes(){
        return  clientRepository.findAll();
    }

    @Override
    public void eliminarCliente(Long id) {
        Optional<ClientEntity> cliente = clientRepository.findById(id);
        cliente.ifPresent(clientEntity -> clientRepository.delete(clientEntity));
    }

    @Override
    public String modificar (ClientEntity cliente){
        Optional<ClientEntity> clienteRepo = clientRepository.obtenerPorEmail(cliente.getEmail());
        if (clienteRepo.isPresent()){
            ClientEntity clienteBD = clienteRepo.get();
            clienteBD.setNombre(cliente.getNombre());
            clienteBD.setFechaNacimiento(cliente.getFechaNacimiento());
            clienteBD.setApellido(cliente.getApellido());
            clientRepository.save(clienteBD);
            return "Guardado";
        }
        return "No se encontró usuario";
    }
}

