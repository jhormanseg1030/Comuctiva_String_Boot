package com.comuctiva.comuctiva.repositoryes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.models.Produc_Carri;
import com.comuctiva.comuctiva.models.Produc_CarriId;


@Repository
public interface Produc_CarriRepositories extends JpaRepository<Produc_Carri,Produc_CarriId>{
    
    @Query("SELECT pc FROM Produc_Carri pc WHERE pc.id.carritoId = :idCarrito AND pc.id.prodId = :idProducto")
    Optional<Produc_Carri> findByCarritoAndProducto(@Param("idCarrito") Integer idCarrito, @Param("idProducto") Integer idProducto);
    
    // ✅ Eliminar producto específico del carrito
    @Modifying
    @Transactional
    @Query("DELETE FROM Produc_Carri pc WHERE pc.id.carritoId = :idCarrito AND pc.id.prodId = :idProducto")
    void deleteByCarritoAndProducto(@Param("idCarrito") Integer idCarrito, @Param("idProducto") Integer idProducto);
    
    // ✅ Vaciar carrito completo
    @Modifying
    @Transactional
    @Query("DELETE FROM Produc_Carri pc WHERE pc.id.carritoId = :idCarrito")
    void deleteByCarritoId(@Param("idCarrito") Integer idCarrito);
    
    // Contar items en el carrito
    @Query("SELECT COUNT(pc) FROM Produc_Carri pc WHERE pc.id.carritoId = :idCarrito")
    Integer countByCarrito(@Param("idCarrito") Integer idCarrito);
    
    // Para Produc_CarriServices (sistema viejo)
    List<Produc_Carri> findByIdProdId(Integer prodId);
    List<Produc_Carri> findByIdCarritoId(Integer carritoId);
}
