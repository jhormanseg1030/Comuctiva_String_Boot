package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comuctiva.comuctiva.models.Comp_Produc;
import com.comuctiva.comuctiva.models.Compra;

@Repository
public interface Comp_ProductRepositories extends JpaRepository<Comp_Produc, Integer> {
    
    // Buscar por ID de compra
    @Query("SELECT cp FROM Comp_Produc cp WHERE cp.compra.id_compra = :compraId")
    List<Comp_Produc> findByIdCompraId(@Param("compraId") Integer compraId);
    
    // Buscar por ID de producto
    @Query("SELECT cp FROM Comp_Produc cp WHERE cp.produc.id_producto = :productoId")
    List<Comp_Produc> findByIdProductoId(@Param("productoId") Integer productoId);
    
    // Buscar por compra
    List<Comp_Produc> findByCompra(Compra compra);
    
    // Buscar por compra y producto (para verificar duplicados)
    @Query("SELECT cp FROM Comp_Produc cp WHERE cp.compra.id_compra = :compraId AND cp.produc.id_producto = :productoId")
    List<Comp_Produc> findByCompraIdAndProductoId(@Param("compraId") Integer compraId, @Param("productoId") Integer productoId);
}
