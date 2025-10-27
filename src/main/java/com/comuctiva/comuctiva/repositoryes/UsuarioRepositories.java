package com.comuctiva.comuctiva.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comuctiva.comuctiva.models.Usuario;

public interface UsuarioRepositories extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.tip_Doc.id_tipdocu = :tipDocId AND u.numDoc = :numDoc AND u.password = :password")
    Usuario findByLogin(@Param("tipDocId") Integer tipDocId, @Param("numDoc") Long numDoc, @Param("password") String password);
    
    Usuario findByNumDoc(Long numDoc);
}
