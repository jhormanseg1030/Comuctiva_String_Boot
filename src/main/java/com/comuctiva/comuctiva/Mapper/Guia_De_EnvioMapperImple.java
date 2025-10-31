package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.Guia_De_EnvioCrearDtos;
import com.comuctiva.comuctiva.Dto.Guia_De_EnvioDto;
import com.comuctiva.comuctiva.models.Guia_De_Envio;
import com.comuctiva.comuctiva.models.Obser;
import com.comuctiva.comuctiva.models.Transportadora;
import com.comuctiva.comuctiva.repositoryes.ObserRepositories;
import com.comuctiva.comuctiva.repositoryes.TransportadoraRepositorie;

import jakarta.persistence.EntityNotFoundException;

@Component
public class Guia_De_EnvioMapperImple implements Guia_de_EnvioMapper{

    private final TransportadoraRepositorie transportadoraRepositorie;
    private final ObserRepositories obserRepositories;

    public Guia_De_EnvioMapperImple(TransportadoraRepositorie transportadoraRepositorie,ObserRepositories obserRepositories){
        this.transportadoraRepositorie=transportadoraRepositorie;
        this.obserRepositories=obserRepositories;
    }
    @Override
    public Guia_De_Envio toGuia_Envio(Guia_De_EnvioCrearDtos guia_EnvioCrearDtos){
        Guia_De_Envio guia_envio = new Guia_De_Envio();
        guia_envio.setFec_env(guia_EnvioCrearDtos.getFech_en());

        Transportadora transportadora = transportadoraRepositorie.findById(guia_EnvioCrearDtos.getTranspId())
        .orElseThrow(() -> new EntityNotFoundException("Transportadora no encontrada"));
        guia_envio.setTransportadora(transportadora);

        Obser obser = obserRepositories.findById(guia_EnvioCrearDtos.getObserId())
        .orElseThrow(() -> new EntityNotFoundException("Observacion no encontrada"));
        guia_envio.setObser(obser);
        return guia_envio;
    }

    @Override
    public Guia_De_EnvioDto toGuia_EnvioDto(Guia_De_Envio guia_envio){
        return new Guia_De_EnvioDto(
            guia_envio.getId_guia(),
            guia_envio.getFec_env(),
            guia_envio.getTransportadora() != null ? guia_envio.getTransportadora().getId_transpor() : null,
            guia_envio.getObser().getId_obser() != null ? guia_envio.getObser().getId_obser() : null);
    }
}
