package com.comuctiva.comuctiva.repositoryes;

import com.comuctiva.comuctiva.models.Cotizacion;
import com.comuctiva.comuctiva.models.EstadoCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CotizacionRepositories extends JpaRepository<Cotizacion, Integer> {
    
    List<Cotizacion> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    
    List<Cotizacion> findByEstado(EstadoCotizacion estado);
    
    List<Cotizacion> findByFechaBetweenAndEstado(LocalDateTime inicio, LocalDateTime fin, EstadoCotizacion estado);
    
    @Query("SELECT c FROM Cotizacion c WHERE c.transportadora.id_transpor = :transportadoraId")
    List<Cotizacion> findByTransportadoraId(@Param("transportadoraId") Integer transportadoraId);
    
    @Query("SELECT c FROM Cotizacion c WHERE c.usuario.id_Usuario = :usuarioId")
    List<Cotizacion> findByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
