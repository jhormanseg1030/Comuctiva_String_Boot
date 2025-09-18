package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.Guia_EnvioCrearDtos;
import com.comuctiva.comuctiva.Dto.Guia_EnvioDto;
import com.comuctiva.comuctiva.Dto.Guia_EnvioUpdateDto;
import com.comuctiva.comuctiva.Mapper.Guia_de_EnvioMapper;
import com.comuctiva.comuctiva.models.Guia_Envio;
import com.comuctiva.comuctiva.models.Obser;
import com.comuctiva.comuctiva.models.Transportadora;
import com.comuctiva.comuctiva.repositoryes.Guia_EnvioRepositories;
import com.comuctiva.comuctiva.repositoryes.ObserRepositories;
import com.comuctiva.comuctiva.repositoryes.TransportadoraRepositorie;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Guia_EnvioServicesImple implements Guia_EnvioServices {

    private final Guia_EnvioRepositories guia_envioRepositories;
    private final Guia_de_EnvioMapper guia_envioMapper;
    private final TransportadoraRepositorie transportadoraRepositorie;
    private final ObserRepositories obserRepositories;
    

    public Guia_EnvioServicesImple(Guia_EnvioRepositories guia_envioRepositories, 
    Guia_de_EnvioMapper guia_envioMapper,
    TransportadoraRepositorie transportadoraRepositorie,
    ObserRepositories obserRepositories) {
        this.guia_envioRepositories = guia_envioRepositories;
        this.guia_envioMapper = guia_envioMapper;
        this.transportadoraRepositorie = transportadoraRepositorie;
        this.obserRepositories = obserRepositories;
    }

    @Override
    @Transactional
    public Guia_EnvioDto crearGuia_Envio(Guia_EnvioCrearDtos guia_EnvioCrearDtos){
        Guia_Envio guia_Envio = guia_envioMapper.toGuia_Envio(guia_EnvioCrearDtos);
        Guia_Envio guia_EnvioGuardado = guia_envioRepositories.save(guia_Envio);
        return guia_envioMapper.toGuia_EnvioDto(guia_EnvioGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public Guia_EnvioDto guia_envioPorId(Integer id){
        Guia_Envio guia_Envio = guia_envioRepositories.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Guia de envio no encontrada con id: "));
        return guia_envioMapper.toGuia_EnvioDto(guia_Envio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Guia_EnvioDto> listartodos(){
        return guia_envioRepositories.findAll()
        .stream()
        .map(guia_envioMapper::toGuia_EnvioDto)
        .collect(Collectors.toList());
    }



    @Override
    @Transactional
    public void eliminarGuia_Envio(Integer id){
        Guia_Envio guia_Envio = guia_envioRepositories.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Guia de envio no encontrada con id: "));
        guia_envioRepositories.delete(guia_Envio);
    }

    @Override
    @Transactional
    public Guia_EnvioDto actualizarGuia_Envio(Guia_EnvioUpdateDto guia_EnvioUpdateDto){
        Guia_Envio guia_Envio = guia_envioRepositories.findById(guia_EnvioUpdateDto.getId_guia())
        .orElseThrow(() -> new EntityNotFoundException("Guia de envio no encontrada con id: "));

        guia_Envio.setFec_env(guia_EnvioUpdateDto.getFech_en());

        Transportadora transportadora = transportadoraRepositorie.findById(guia_EnvioUpdateDto.getTranspId())
        .orElseThrow(() -> new EntityNotFoundException("Transportadora no encontrada"));
        guia_Envio.setTransportadora(transportadora);

        Obser obser = obserRepositories.findById(guia_EnvioUpdateDto.getObserId())
        .orElseThrow(() -> new EntityNotFoundException("Observacion no encontrada"));
        guia_Envio.setObser(obser);

        Guia_Envio guia_EnvioActualizado = guia_envioRepositories.save(guia_Envio);
        return guia_envioMapper.toGuia_EnvioDto(guia_EnvioActualizado);
    }
}
