package com.comuctiva.comuctiva.repositoryes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comuctiva.comuctiva.models.Carrito;

public interface CarritoRepositories extends JpaRepository <Carrito,Integer>{

    @Query("SELECT c FROM Carrito c LEFT JOIN FETCH c.items WHERE c.usuario.id_Usuario = :id_usuario")
    Optional<Carrito> findByUsuarioId_usuarioWithItems(@Param("id_usuario") Integer id_usuario);
    
    // Verificar si el usuario ya tiene carrito
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Carrito c WHERE c.usuario.id_Usuario = :id_usuario")
    boolean existsByUsuarioId_usuario(@Param("id_usuario") Integer id_usuario);
}
