package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comuctiva.comuctiva.models.Producto;

public interface ProductoRepositorie extends JpaRepository<Producto, Integer> {

	@Query("SELECT p FROM Producto p WHERE p.vendedor.id_Usuario = :id_Usuario")
    List<Producto> findByVendedorId_Usuario(@Param("id_Usuario") Integer id_Usuario);

    @Modifying
    @Query("UPDATE Producto p SET p.activo = false WHERE p.id_producto = :id")
    void softDelete(@Param("id") Integer id);
    
    @Modifying
    @Query("UPDATE Producto p SET p.activo = true WHERE p.id_producto = :id")
    void restore(@Param("id") Integer id);

    List<Producto> findByActivoTrue();

    @Query("SELECT p FROM Producto p WHERE p.vendedor.id_Usuario = :id_Usuario AND p.activo = true")
    List<Producto> findByVendedorId_UsuarioAndActivoTrue(@Param("id_Usuario") Integer id_Usuario);
}
