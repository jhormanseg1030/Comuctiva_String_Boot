package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;
import com.comuctiva.comuctiva.Dto.DireccionesCreateDto;
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
    public Direcciones toDirecciones(DireccionesCreateDto direccionesCreateDto){
        Direcciones direcciones = new Direcciones();
        direcciones.setNum(direccionesCreateDto.getNume());
        direcciones.setComple(direccionesCreateDto.getCompl());
        direcciones.setUbi_geo(direccionesCreateDto.getUbic_geo());

        Barrio barrio = barrioRepositories.findById(direccionesCreateDto.getBarrioId())
        .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado"));
        direcciones.setBarrio(barrio);

        Vias vias = viasRepositories.findById(direccionesCreateDto.getViasId())
        .orElseThrow(() -> new EntityNotFoundException("Vias no encontrado"));
        direcciones.setVias(vias);

        Usuario usuariosId = usuarioRepositories.findById(direccionesCreateDto.getUsuariosId())
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
}
