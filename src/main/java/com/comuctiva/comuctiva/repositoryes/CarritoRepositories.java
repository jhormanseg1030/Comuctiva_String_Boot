package com.comuctiva.comuctiva.repositoryes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comuctiva.comuctiva.models.Carrito;

public interface CarritoRepositories extends JpaRepository <Carrito,Integer>{

    @Query("SELECT c FROM Carrito c LEFT JOIN FETCH c.items WHERE c.usuario.id_Usuario = :id_Usuario")
    Optional<Carrito> findByUsuarioId_UsuarioWithItems(@Param("id_Usuario") Integer id_Usuario);
    
    // Verificar si el usuario ya tiene carrito
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Carrito c WHERE c.usuario.id_Usuario = :id_Usuario")
    boolean existsByUsuarioId_Usuario(@Param("id_Usuario") Integer id_Usuario);
}
