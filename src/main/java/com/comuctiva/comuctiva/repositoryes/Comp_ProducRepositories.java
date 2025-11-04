package com.comuctiva.comuctiva.repositoryes;

import com.comuctiva.comuctiva.models.Comp_Produc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Comp_ProducRepositories extends JpaRepository<Comp_Produc, Integer> {
    
    // Buscar productos comprados por una compra específica
    @Query("SELECT cp FROM Comp_Produc cp WHERE cp.compra.id_compra = :idCompra")
    List<Comp_Produc> findByCompraId(@Param("idCompra") Long idCompra);
    
    // Buscar compras de un producto específico
    @Query("SELECT cp FROM Comp_Produc cp WHERE cp.produc.id_producto = :idProducto")
    List<Comp_Produc> findByProductoId(@Param("idProducto") Long idProducto);
    
    /**
     * Obtiene las ventas de un vendedor específico
     * (productos que le compraron al usuario)
     */
    @Query("""
        SELECT cp 
        FROM Comp_Produc cp
        JOIN FETCH cp.compra c
        JOIN FETCH c.pedido p
        JOIN FETCH p.usuario u
        JOIN FETCH cp.produc pr
        WHERE pr.vendedor.id_Usuario = :idVendedor
        ORDER BY c.fec_com DESC
    """)
    List<Comp_Produc> findVentasByVendedor(@Param("idVendedor") Integer idVendedor);
    
    /**
     * Obtiene las compras de un cliente específico
     * (productos que el usuario compró)
     */
    @Query("""
        SELECT cp 
        FROM Comp_Produc cp
        JOIN FETCH cp.compra c
        JOIN FETCH c.pedido p
        JOIN FETCH cp.produc pr
        LEFT JOIN FETCH pr.vendedor v
        WHERE p.usuario.id_Usuario = :idCliente
        ORDER BY c.fec_com DESC
    """)
    List<Comp_Produc> findComprasByCliente(@Param("idCliente") Integer idCliente);
}
