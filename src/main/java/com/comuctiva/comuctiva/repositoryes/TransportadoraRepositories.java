package com.comuctiva.comuctiva.repositoryes;

import com.comuctiva.comuctiva.models.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportadoraRepositories extends JpaRepository<Transportadora, Integer> {
    
    Optional<Transportadora> findByCorreo(String correo);
    
    boolean existsByCorreo(String correo);
}
