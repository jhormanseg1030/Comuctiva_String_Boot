package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.MuniDto;
import com.comuctiva.comuctiva.Mapper.MuniMapper;
import com.comuctiva.comuctiva.models.Muni;
import com.comuctiva.comuctiva.repositoryes.MuniRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MuniServicesImple implements MuniServices {

    public final MuniRepositories muniRepositories;
    public final MuniMapper muniMapper;

    public MuniServicesImple(MuniRepositories muniRepositories, MuniMapper muniMapper) {
        this.muniRepositories = muniRepositories;
        this.muniMapper = muniMapper;
    }

    @Override
    public MuniDto crearMuniDto(MuniDto muniDto){
        Muni muni = muniMapper.toMuni(muniDto);
        Muni muniGuardado = muniRepositories.save(muni);
        return muniMapper.toMuniDto(muniGuardado);
    }

    @Override
    public MuniDto muniPorId(Integer id){
        return muniRepositories.findById(id)
        .map(muniMapper::toMuniDto)
        .orElseThrow(()-> new EntityNotFoundException("Municipio no encontrado con id: "+id));
    }
    @Override
    public List<MuniDto> listartodos(){
        return muniRepositories.findAll()
        .stream()
        .map(muniMapper::toMuniDto)
        .collect(Collectors.toList());
    }

    @Override
    public void updateMuni(Integer id, MuniDto muniDto){
        Muni muniExistente = muniRepositories.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado con id: " + id));
        muniMapper.updateMuni(muniExistente, muniDto);
        muniRepositories.save(muniExistente);
    }
}
