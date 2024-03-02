package com.example.ProyectoCoder.repository;

import com.example.ProyectoCoder.models.ProductoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoVentaRepository extends JpaRepository<ProductoVenta, Long> {
    @Query(value = "SELECT * FROM producto_venta WHERE venta_id = :idVenta AND producto_id = :idProducto" , nativeQuery = true)
    ProductoVenta findProductSale(@Param("idVenta") Long venta, @Param("idProducto") Long producto);
}
