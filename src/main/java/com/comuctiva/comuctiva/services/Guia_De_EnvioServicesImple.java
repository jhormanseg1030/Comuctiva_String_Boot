package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.Guia_De_EnvioCrearDtos;
import com.comuctiva.comuctiva.Dto.Guia_De_EnvioDto;
import com.comuctiva.comuctiva.Dto.Guia_De_EnvioUpdateDto;
import com.comuctiva.comuctiva.Mapper.Guia_de_EnvioMapper;
import com.comuctiva.comuctiva.models.Guia_De_Envio;
import com.comuctiva.comuctiva.models.Obser;
import com.comuctiva.comuctiva.models.Transportadora;
import com.comuctiva.comuctiva.repositoryes.Guia_De_EnvioRepositories;
import com.comuctiva.comuctiva.repositoryes.ObserRepositories;
import com.comuctiva.comuctiva.repositoryes.TransportadoraRepositorie;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Guia_De_EnvioServicesImple implements Guia_De_EnvioServices {

    private final Guia_De_EnvioRepositories guia_envioRepositories;
    private final Guia_de_EnvioMapper guia_envioMapper;
    private final TransportadoraRepositorie transportadoraRepositorie;
    private final ObserRepositories obserRepositories;
    

    public Guia_De_EnvioServicesImple(Guia_De_EnvioRepositories guia_envioRepositories, 
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
    public Guia_De_EnvioDto crearGuia_Envio(Guia_De_EnvioCrearDtos guia_EnvioCrearDtos){
        Guia_De_Envio guia_Envio = guia_envioMapper.toGuia_Envio(guia_EnvioCrearDtos);
        Guia_De_Envio guia_EnvioGuardado = guia_envioRepositories.save(guia_Envio);
        return guia_envioMapper.toGuia_EnvioDto(guia_EnvioGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public Guia_De_EnvioDto guia_envioPorId(Integer id_gui){
        Guia_De_Envio guia_Envio = guia_envioRepositories.findById(id_gui)
        .orElseThrow(() -> new EntityNotFoundException("Guia de envio no encontrada con id: "));
        return guia_envioMapper.toGuia_EnvioDto(guia_Envio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Guia_De_EnvioDto> listartodos(){
        return guia_envioRepositories.findAll()
        .stream()
        .map(guia_envioMapper::toGuia_EnvioDto)
        .collect(Collectors.toList());
    }



    @Override
    @Transactional
    public void eliminarGuia_Envio(Integer id_gui){
        Guia_De_Envio guia_Envio = guia_envioRepositories.findById(id_gui)
        .orElseThrow(() -> new EntityNotFoundException("Guia de envio no encontrada con id: "));
        guia_envioRepositories.delete(guia_Envio);
    }

    @Override
    @Transactional
    public Guia_De_EnvioDto actualizarGuia_Envio(Guia_De_EnvioUpdateDto guia_EnvioUpdateDto){
        Guia_De_Envio guia_Envio = guia_envioRepositories.findById(guia_EnvioUpdateDto.getId_guia())
        .orElseThrow(() -> new EntityNotFoundException("Guia de envio no encontrada con id: "));

        guia_Envio.setFec_env(guia_EnvioUpdateDto.getFech_en());

        Transportadora transportadora = transportadoraRepositorie.findById(guia_EnvioUpdateDto.getTranspId())
        .orElseThrow(() -> new EntityNotFoundException("Transportadora no encontrada"));
        guia_Envio.setTransportadora(transportadora);

        Obser obser = obserRepositories.findById(guia_EnvioUpdateDto.getObserId())
        .orElseThrow(() -> new EntityNotFoundException("Observacion no encontrada"));
        guia_Envio.setObser(obser);

        Guia_De_Envio guia_EnvioActualizado = guia_envioRepositories.save(guia_Envio);
        return guia_envioMapper.toGuia_EnvioDto(guia_EnvioActualizado);
    }
}
