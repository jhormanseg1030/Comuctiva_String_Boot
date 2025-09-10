package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.Guia_EnvioDto;
import com.comuctiva.comuctiva.Mapper.Guia_de_EnvioMapper;
import com.comuctiva.comuctiva.models.Guia_Envio;
import com.comuctiva.comuctiva.repositoryes.Guia_EnvioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Guia_EnvioServicesImple implements Guia_EnvioServices {

    public final Guia_EnvioRepositories guia_envioRepositories;
    public final Guia_de_EnvioMapper guia_envioMapper;

    public Guia_EnvioServicesImple(Guia_EnvioRepositories guia_envioRepositories, Guia_de_EnvioMapper guia_envioMapper) {
        this.guia_envioRepositories = guia_envioRepositories;
        this.guia_envioMapper = guia_envioMapper;
    }

    @Override
    public Guia_EnvioDto crearGuia_Envio(Guia_EnvioDto guia_EnvioDto){
        Guia_Envio guia_Envio = guia_envioMapper.toGuia_Envio(guia_EnvioDto);
        Guia_Envio guia_EnvioGuardado = guia_envioRepositories.save(guia_Envio);
        return guia_envioMapper.toGuia_EnvioDto(guia_EnvioGuardado);
    }

    @Override
    public Guia_EnvioDto guia_envioPorId(Integer id){
        return guia_envioRepositories.findById(id)
        .map(guia_envioMapper::toGuia_EnvioDto)
        .orElseThrow(()-> new EntityNotFoundException("Guia de envio no encontrada con id: "+id));
    }

    @Override
    public List<Guia_EnvioDto> listartodos(){
        return guia_envioRepositories.findAll()
        .stream()
        .map(guia_envioMapper::toGuia_EnvioDto)
        .collect(Collectors.toList());
    }

    @Override
    public void eliminarGuia_Envio(Integer id){
        guia_envioRepositories.deleteById(id);
    }
}
