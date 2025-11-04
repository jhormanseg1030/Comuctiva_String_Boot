package com.comuctiva.comuctiva.services;

import com.comuctiva.comuctiva.Dto.VehiculoCreateDto;
import com.comuctiva.comuctiva.Dto.VehiculoDto;
import com.comuctiva.comuctiva.Dto.VehiculoEstadoDto;
import com.comuctiva.comuctiva.Dto.VehiculoUpdateDto;
import com.comuctiva.comuctiva.Mapper.VehiculoMapper;
import com.comuctiva.comuctiva.models.Vehiculo;
import com.comuctiva.comuctiva.repositoryes.VehiculoRepositories;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoServicesImple implements VehiculoServices {
    
    private final VehiculoRepositories vehiculoRepositories;
    private final VehiculoMapper vehiculoMapper;
    
    public VehiculoServicesImple(VehiculoRepositories vehiculoRepositories, VehiculoMapper vehiculoMapper) {
        this.vehiculoRepositories = vehiculoRepositories;
        this.vehiculoMapper = vehiculoMapper;
    }
    
    @Override
    public List<VehiculoDto> listarTodos() {
        return vehiculoRepositories.findAll().stream()
                .map(vehiculoMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public VehiculoDto buscarPorId(Integer id) {
        Vehiculo vehiculo = vehiculoRepositories.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehículo no encontrado con ID: " + id));
        return vehiculoMapper.toDto(vehiculo);
    }
    
    @Override
    @Transactional
    public VehiculoDto crear(VehiculoCreateDto createDto) {
        // Verificar si la placa ya existe
        if (vehiculoRepositories.existsByPlaca(createDto.getPlaca())) {
            throw new IllegalStateException("Ya existe un vehículo con la placa: " + createDto.getPlaca());
        }
        
        Vehiculo vehiculo = vehiculoMapper.toEntity(createDto);
        Vehiculo guardado = vehiculoRepositories.save(vehiculo);
        return vehiculoMapper.toDto(guardado);
    }
    
    @Override
    @Transactional
    public VehiculoDto actualizar(VehiculoUpdateDto updateDto) {
        Vehiculo vehiculo = vehiculoRepositories.findById(updateDto.getId_vehiculo())
                .orElseThrow(() -> new EntityNotFoundException("Vehículo no encontrado con ID: " + updateDto.getId_vehiculo()));
        
        vehiculoMapper.updateEntityFromDto(updateDto, vehiculo);
        Vehiculo actualizado = vehiculoRepositories.save(vehiculo);
        return vehiculoMapper.toDto(actualizado);
    }
    
    @Override
    @Transactional
    public VehiculoDto cambiarEstado(Integer id, VehiculoEstadoDto estadoDto) {
        Vehiculo vehiculo = vehiculoRepositories.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehículo no encontrado con ID: " + id));
        
        vehiculo.setEstado(estadoDto.getEstado());
        
        if (estadoDto.getUbicacion() != null) {
            vehiculo.setUbicacion(estadoDto.getUbicacion());
        }
        
        if (estadoDto.getMantenimiento() != null) {
            vehiculo.setMantenimiento(estadoDto.getMantenimiento());
        }
        
        Vehiculo actualizado = vehiculoRepositories.save(vehiculo);
        return vehiculoMapper.toDto(actualizado);
    }
    
    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!vehiculoRepositories.existsById(id)) {
            throw new EntityNotFoundException("Vehículo no encontrado con ID: " + id);
        }
        vehiculoRepositories.deleteById(id);
    }
}
