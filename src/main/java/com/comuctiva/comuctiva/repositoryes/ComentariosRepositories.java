package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comuctiva.comuctiva.models.Comentarios;

public interface ComentariosRepositories extends JpaRepository<Comentarios, Integer> {
    
    // Buscar comentarios por ID de producto
    @Query("SELECT c FROM Comentarios c JOIN c.compProduc cp WHERE cp.producto.id_producto = :idProducto")
    List<Comentarios> findByProductoId(@Param("idProducto") Integer idProducto);
    
    // Buscar comentarios por ID de usuario
    @Query("SELECT c FROM Comentarios c WHERE c.usuario.id_Usuario = :idUsuario")
    List<Comentarios> findByUsuarioId(@Param("idUsuario") Integer idUsuario);
}
