package com.example.ProyectoCoder.repository;

import com.example.ProyectoCoder.models.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
