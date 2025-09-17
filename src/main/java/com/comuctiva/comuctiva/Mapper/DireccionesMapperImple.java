package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.DireccionesDto;
import com.comuctiva.comuctiva.models.Barrio;
import com.comuctiva.comuctiva.models.Direcciones;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.models.Vias;
import com.comuctiva.comuctiva.repositoryes.BarrioRepositories;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import com.comuctiva.comuctiva.repositoryes.ViasRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class DireccionesMapperImple implements DireccionesMapper{

    private final BarrioRepositories barrioRepositories;
    private final ViasRepositories viasRepositories;
    private final UsuarioRepositories usuarioRepositories;

    public DireccionesMapperImple(BarrioRepositories barrioRepositories, ViasRepositories viasRepositories, UsuarioRepositories usuarioRepositories){
        this.barrioRepositories=barrioRepositories;
        this.viasRepositories=viasRepositories;
        this.usuarioRepositories=usuarioRepositories;
    }

    @Override
    public Direcciones toDirecciones(DireccionesDto direccionesDto){
        Direcciones direcciones = new Direcciones();
        direcciones.setId_direcc(direccionesDto.getId_direc());
        direcciones.setNum(direccionesDto.getNume());
        direcciones.setComple(direccionesDto.getCompl());
        direcciones.setUbi_geo(direccionesDto.getUbic_geo());

        Barrio barrio = barrioRepositories.findById(direccionesDto.getBarrioId())
        .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado"));
        direcciones.setBarrio(barrio);

        Vias vias = viasRepositories.findById(direccionesDto.getViasId())
        .orElseThrow(() -> new EntityNotFoundException("Vias no encontrado"));
        direcciones.setVias(vias);

        Usuario usuariosId = usuarioRepositories.findById(direccionesDto.getUsuariosId())
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        direcciones.setUsuario(usuariosId);
        return direcciones;
    }

    @Override
    public DireccionesDto toDireccionesDto(Direcciones direcciones){
        return new DireccionesDto(
            direcciones.getId_direcc(),
            direcciones.getNum(),
            direcciones.getComple(),
            direcciones.getUbi_geo(),
            direcciones.getBarrio().getId_barrio(),
            direcciones.getUsuario().getId_Usuario(),
            direcciones.getVias().getId_vias());
    }

    @Override
    public List<DireccionesDto> toDireccionesDtoList(List<Direcciones>direccioness){
        if (direccioness == null) {
            return List.of();
        }
        List<DireccionesDto>direccionesDtos=new ArrayList<DireccionesDto>(direccioness.size());
        for(Direcciones direcciones : direccioness){
            direccionesDtos.add(toDireccionesDto(direcciones));
        }
        return direccionesDtos;
    }
    
    @Override
    public void updateDirecciones(Direcciones direcciones, DireccionesDto direccionesDto){
        if(direccionesDto == null){
            return;
        }
        direcciones.setId_direcc(direccionesDto.getId_direc());
        direcciones.setComple(direccionesDto.getCompl());
        direcciones.setNum(direccionesDto.getNume());
        direcciones.setUbi_geo(direccionesDto.getUbic_geo());
    }
}
