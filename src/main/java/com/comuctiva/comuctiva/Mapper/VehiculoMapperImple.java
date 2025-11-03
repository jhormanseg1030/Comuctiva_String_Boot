package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.VehiculoCreateDto;
import com.comuctiva.comuctiva.Dto.VehiculoDto;
import com.comuctiva.comuctiva.Dto.VehiculoUpdateDto;
import com.comuctiva.comuctiva.models.Transportadora;
import com.comuctiva.comuctiva.models.Vehiculo;
import com.comuctiva.comuctiva.repositoryes.TransportadoraRepositories;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class VehiculoMapperImple implements VehiculoMapper {
    
    private final TransportadoraRepositories transportadoraRepositories;
    
    public VehiculoMapperImple(TransportadoraRepositories transportadoraRepositories) {
        this.transportadoraRepositories = transportadoraRepositories;
    }
    
    @Override
    public VehiculoDto toDto(Vehiculo vehiculo) {
        VehiculoDto dto = new VehiculoDto();
        dto.setId_vehiculo(vehiculo.getId_vehiculo());
        dto.setTipo(vehiculo.getTipo());
        dto.setNombre(vehiculo.getNombre());
        dto.setPlaca(vehiculo.getPlaca());
        dto.setConductor(vehiculo.getConductor());
        dto.setCapacidadKg(vehiculo.getCapacidadKg());
        dto.setEstado(vehiculo.getEstado());
        dto.setMantenimiento(vehiculo.getMantenimiento());
        dto.setUbicacion(vehiculo.getUbicacion());
        dto.setViajesMes(vehiculo.getViajesMes());
        dto.setIngresosMes(vehiculo.getIngresosMes());
        
        if (vehiculo.getTransportadora() != null) {
            dto.setId_transportadora(vehiculo.getTransportadora().getId_transpor());
            dto.setNombreTransportadora(vehiculo.getTransportadora().getNombre());
        }
        
        return dto;
    }
    
    @Override
    public Vehiculo toEntity(VehiculoCreateDto createDto) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(createDto.getTipo());
        vehiculo.setNombre(createDto.getNombre());
        vehiculo.setPlaca(createDto.getPlaca());
        vehiculo.setConductor(createDto.getConductor());
        vehiculo.setCapacidadKg(createDto.getCapacidadKg());
        vehiculo.setEstado(createDto.getEstado());
        vehiculo.setMantenimiento(createDto.getMantenimiento() != null ? createDto.getMantenimiento() : false);
        vehiculo.setUbicacion(createDto.getUbicacion());
        vehiculo.setViajesMes(0);
        vehiculo.setIngresosMes(0.0);
        
        Transportadora transportadora = transportadoraRepositories.findById(createDto.getId_transportadora())
                .orElseThrow(() -> new EntityNotFoundException("Transportadora no encontrada con ID: " + createDto.getId_transportadora()));
        vehiculo.setTransportadora(transportadora);
        
        return vehiculo;
    }
    
    @Override
    public void updateEntityFromDto(VehiculoUpdateDto updateDto, Vehiculo vehiculo) {
        if (updateDto.getNombre() != null) {
            vehiculo.setNombre(updateDto.getNombre());
        }
        if (updateDto.getConductor() != null) {
            vehiculo.setConductor(updateDto.getConductor());
        }
        if (updateDto.getEstado() != null) {
            vehiculo.setEstado(updateDto.getEstado());
        }
        if (updateDto.getMantenimiento() != null) {
            vehiculo.setMantenimiento(updateDto.getMantenimiento());
        }
        if (updateDto.getUbicacion() != null) {
            vehiculo.setUbicacion(updateDto.getUbicacion());
        }
        if (updateDto.getViajesMes() != null) {
            vehiculo.setViajesMes(updateDto.getViajesMes());
        }
        if (updateDto.getIngresosMes() != null) {
            vehiculo.setIngresosMes(updateDto.getIngresosMes());
        }
    }
}
