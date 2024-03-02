package com.example.ProyectoCoder.services;

import com.example.ProyectoCoder.models.ClientEntity;
import com.example.ProyectoCoder.models.dto.ClientResponse;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    ClientEntity altaCliente(ClientEntity client);

    Optional<ClientEntity> buscarPorId(long id);

    List<ClientEntity> obtenerClientes();

    void eliminarCliente(Long id);

    String modificar(ClientEntity client);

    ClientResponse obtenerEdad(Long id);
}
