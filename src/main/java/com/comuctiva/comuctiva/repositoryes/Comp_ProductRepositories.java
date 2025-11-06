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
    
    @Query("SELECT cp FROM Comp_Produc cp WHERE cp.compra.id_compra = :id_compra")
    List<Comp_Produc> findByCompra_Id_compra(@Param("id_compra") Integer id_compra);
    
    @Query("SELECT cp FROM Comp_Produc cp WHERE cp.producto.id_producto = :productoId")
    List<Comp_Produc> findByIdProductoId(@Param("productoId") Integer productoId);
    
    List<Comp_Produc> findByCompra(Compra compra);

    @Query("SELECT cp FROM Comp_Produc cp WHERE cp.producto.vendedor.id_Usuario = :id_usuario")
    List<Comp_Produc> findVentasByVendedor(@Param("id_usuario") Integer id_usuario);
}
