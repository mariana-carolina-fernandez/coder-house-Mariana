package com.example.ProyectoCoder.repository;

import com.example.ProyectoCoder.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {


    @Query(value = "SELECT * FROM client c WHERE c.correo = :email", nativeQuery = true)
    Optional <ClientEntity> obtenerPorEmail(String email);
}
