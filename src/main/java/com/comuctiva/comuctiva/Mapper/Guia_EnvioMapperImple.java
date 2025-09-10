package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.Guia_EnvioDto;
import com.comuctiva.comuctiva.models.Guia_Envio;
import com.comuctiva.comuctiva.models.Obser;
import com.comuctiva.comuctiva.models.Transportadora;
import com.comuctiva.comuctiva.repositoryes.ObserRepositories;
import com.comuctiva.comuctiva.repositoryes.TransportadoraRepositorie;

import jakarta.persistence.EntityNotFoundException;

@Component
public class Guia_EnvioMapperImple implements Guia_de_EnvioMapper{

    private final TransportadoraRepositorie transportadoraRepositorie;
    private final ObserRepositories obserRepositories;

    public Guia_EnvioMapperImple(TransportadoraRepositorie transportadoraRepositorie,ObserRepositories obserRepositories){
        this.transportadoraRepositorie=transportadoraRepositorie;
        this.obserRepositories=obserRepositories;
    }
    @Override
    public Guia_Envio toGuia_Envio(Guia_EnvioDto guia_envioDto){
        Guia_Envio guia_envio = new Guia_Envio();
        guia_envio.setId_guia(guia_envioDto.getId_gui());
        guia_envio.setFec_env(guia_envioDto.getFech_en());

        Transportadora transportadora = transportadoraRepositorie.findById(guia_envioDto.getTranspId())
        .orElseThrow(() -> new EntityNotFoundException("Transportadora no encontrada"));
        guia_envio.setTransportadora(transportadora);

    Obser obser = obserRepositories.findById(guia_envioDto.getObserId())
        .orElseThrow(() -> new EntityNotFoundException("Observacion no encontrada"));
        guia_envio.setObser(obser);
        return guia_envio;
    }

    @Override
    public Guia_EnvioDto toGuia_EnvioDto(Guia_Envio guia_envio){
        return new Guia_EnvioDto(
            guia_envio.getId_guia(),
            guia_envio.getFec_env(),
            guia_envio.getTransportadora().getId_transpor(),
            guia_envio.getObser().getId_obser());
    }

    @Override
    public List<Guia_EnvioDto>toGuia_EnvioDtoList(List<Guia_Envio> guia_envios){
        if (guia_envios== null) {
            return List.of();
        }
        List<Guia_EnvioDto> guia_envioDtos = new ArrayList<Guia_EnvioDto>(guia_envios.size());
        for(Guia_Envio guia_Envio: guia_envios){
            guia_envioDtos.add(toGuia_EnvioDto(guia_Envio));
        }
        return guia_envioDtos;
    }
    @Override
    public void updateGuia_Envio(Guia_Envio guia_envio,Guia_EnvioDto guia_envioDto){
        if (guia_envioDto == null) {
            return;
        }
        guia_envio.setId_guia(guia_envioDto.getId_gui());
        guia_envio.setFec_env(guia_envioDto.getFech_en());
    }
}
