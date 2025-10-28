package com.comuctiva.comuctiva.repositoryes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comuctiva.comuctiva.models.Usuario;

public interface UsuarioRepositories extends JpaRepository<Usuario, Integer> {
    // Buscar usuarios por tipo de documento y número de documento (puede retornar múltiples)
    @Query("SELECT u FROM Usuario u WHERE u.tip_Doc.id_tipdocu = :tipDocId AND u.numDoc = :numDoc")
    List<Usuario> findAllByTipDocAndNumDoc(@Param("tipDocId") Integer tipDocId, @Param("numDoc") Long numDoc);
    
    // Buscar el primer usuario por tipo de documento y número de documento
    @Query("SELECT u FROM Usuario u WHERE u.tip_Doc.id_tipdocu = :tipDocId AND u.numDoc = :numDoc ORDER BY u.id_Usuario ASC")
    Optional<Usuario> findFirstByTipDocAndNumDoc(@Param("tipDocId") Integer tipDocId, @Param("numDoc") Long numDoc);
    
    Usuario findByNumDoc(Long numDoc);
}
