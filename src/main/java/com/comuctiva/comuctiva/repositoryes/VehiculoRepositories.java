package com.comuctiva.comuctiva.repositoryes;

import com.comuctiva.comuctiva.models.EstadoVehiculo;
import com.comuctiva.comuctiva.models.TipoVehiculo;
import com.comuctiva.comuctiva.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepositories extends JpaRepository<Vehiculo, Integer> {
    
    List<Vehiculo> findByEstado(EstadoVehiculo estado);
    
    List<Vehiculo> findByTipo(TipoVehiculo tipo);
    
    @Query("SELECT v FROM Vehiculo v WHERE v.transportadora.id_transpor = :transportadoraId")
    List<Vehiculo> findByTransportadoraId(@Param("transportadoraId") Integer transportadoraId);
    
    Optional<Vehiculo> findByPlaca(String placa);
    
    boolean existsByPlaca(String placa);
}
