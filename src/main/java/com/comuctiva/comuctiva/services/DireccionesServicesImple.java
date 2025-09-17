package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.DireccionesDto;
import com.comuctiva.comuctiva.Mapper.DireccionesMapper;
import com.comuctiva.comuctiva.models.Direcciones;
import com.comuctiva.comuctiva.repositoryes.DireccionesRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DireccionesServicesImple implements DireccionesServices {

    private final DireccionesRepositories direccionesRepositories;
    private final DireccionesMapper direccionesMapper;

    public DireccionesServicesImple(DireccionesRepositories direccionesRepositories, DireccionesMapper direccionesMapper) {
        this.direccionesRepositories = direccionesRepositories;
        this.direccionesMapper = direccionesMapper;
    }

    @Override
    public DireccionesDto crearDireccion(DireccionesDto direccionesDto) {
        Direcciones direcciones = direccionesMapper.toDirecciones(direccionesDto);
        Direcciones direccionesGuardado = direccionesRepositories.save(direcciones);
        return direccionesMapper.toDireccionesDto(direccionesGuardado);
    }

    @Override
    public DireccionesDto direccionPorId(Integer id) {
        return direccionesRepositories.findById(id)
                .map(direccionesMapper::toDireccionesDto)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada"));
    }

    @Override
    public List<DireccionesDto> listarTodas() {
        return direccionesRepositories.findAll()
                .stream()
                .map(direccionesMapper::toDireccionesDto)
                .collect(Collectors.toList());
    }
    @Override
    public void eliminarDireccion(Integer id) {
        direccionesRepositories.deleteById(id);
    }
}
