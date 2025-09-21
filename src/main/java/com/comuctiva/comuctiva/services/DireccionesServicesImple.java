package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.DireccionesCreateDto;
import com.comuctiva.comuctiva.Dto.DireccionesDto;
import com.comuctiva.comuctiva.Dto.DireccionesUpdateDto;
import com.comuctiva.comuctiva.Mapper.DireccionesMapper;
import com.comuctiva.comuctiva.models.Barrio;
import com.comuctiva.comuctiva.models.Direcciones;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.models.Vias;
import com.comuctiva.comuctiva.repositoryes.BarrioRepositories;
import com.comuctiva.comuctiva.repositoryes.DireccionesRepositories;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import com.comuctiva.comuctiva.repositoryes.ViasRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DireccionesServicesImple implements DireccionesServices {

    private final DireccionesRepositories direccionesRepositories;
    private final DireccionesMapper direccionesMapper;
    private final ViasRepositories viasRepositories;
    private final UsuarioRepositories usuarioRepositories;
    private final BarrioRepositories barrioRepositories;

    public DireccionesServicesImple(DireccionesRepositories direccionesRepositories, 
    DireccionesMapper direccionesMapper, 
    ViasRepositories viasRepositories, 
    UsuarioRepositories usuarioRepositories, 
    BarrioRepositories barrioRepositories) 
    
    {
        this.direccionesRepositories = direccionesRepositories;
        this.direccionesMapper = direccionesMapper;
        this.viasRepositories = viasRepositories;
        this.usuarioRepositories = usuarioRepositories;
        this.barrioRepositories = barrioRepositories;
    }

    @Override
    @Transactional
    public DireccionesDto crearDireccion(DireccionesCreateDto direccionescCreateDto) {
        Direcciones direcciones = direccionesMapper.toDirecciones(direccionescCreateDto);
        Direcciones direccionesGuardado = direccionesRepositories.save(direcciones);
        return direccionesMapper.toDireccionesDto(direccionesGuardado);
    }

    @Override
    @Transactional()
    public DireccionesDto direccionPorId(Integer id) {
        Direcciones direcciones = direccionesRepositories.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada"));
                return direccionesMapper.toDireccionesDto(direcciones);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DireccionesDto> listarTodas() {
        return direccionesRepositories.findAll()
                .stream()
                .map(direccionesMapper::toDireccionesDto)
                .collect(Collectors.toList());
    }
    @Override
    public void eliminarDireccion(Integer id) {
        Direcciones direccionesElimi = direccionesRepositories.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada"));
        direccionesRepositories.save(direccionesElimi);
    }

    @Override
    @Transactional
    public DireccionesDto actualizarDireccion( DireccionesUpdateDto direccionesUpdateDto) {
        Direcciones direcciones = direccionesRepositories.findById(direccionesUpdateDto.getId_direc())
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada"));

        direcciones.setNum(direccionesUpdateDto.getNume());
        direcciones.setComple(direccionesUpdateDto.getCompl());
        direcciones.setUbi_geo(direccionesUpdateDto.getUbic_geo());

        Barrio barrio = barrioRepositories.findById(direccionesUpdateDto.getBarrioId())
                .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado"));
        direcciones.setBarrio(barrio);

        Vias vias = viasRepositories.findById(direccionesUpdateDto.getViasId())
                .orElseThrow(() -> new EntityNotFoundException("Vias no encontrado"));
        direcciones.setVias(vias);

        Usuario usuariosId = usuarioRepositories.findById(direccionesUpdateDto.getUsuariosId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        direcciones.setUsuario(usuariosId);
        Direcciones direccionesGuardado = direccionesRepositories.save(direcciones);
        return direccionesMapper.toDireccionesDto(direccionesGuardado);
    }
}
