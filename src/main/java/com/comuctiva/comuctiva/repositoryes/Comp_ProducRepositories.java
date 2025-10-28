package com.comuctiva.comuctiva.repositoryes;

import com.comuctiva.comuctiva.models.Comp_Produc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Comp_ProducRepositories extends JpaRepository<Comp_Produc, Long> {
    
    // Buscar productos comprados por una compra específica
    @Query("SELECT cp FROM Comp_Produc cp WHERE cp.compra.id_compra = :idCompra")
    List<Comp_Produc> findByCompraId(@Param("idCompra") Long idCompra);
    
    // Buscar compras de un producto específico
    @Query("SELECT cp FROM Comp_Produc cp WHERE cp.produc.id_producto = :idProducto")
    List<Comp_Produc> findByProductoId(@Param("idProducto") Long idProducto);
}
