package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.MuniDto;
import com.comuctiva.comuctiva.models.Departamento;
import com.comuctiva.comuctiva.models.Muni;
import com.comuctiva.comuctiva.repositoryes.DepartamentoRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class MuniMapperImple implements MuniMapper {

    private final DepartamentoRepositories departamentoRepositories;

    public MuniMapperImple(DepartamentoRepositories departamentoRepositories){
        this.departamentoRepositories=departamentoRepositories;
    }
    
    @Override
    public Muni toMuni(MuniDto muniDto){
        Muni muni = new Muni();
        muni.setId_muni(muniDto.getId_munic());
        muni.setNom(muniDto.getNombr());

        Departamento departamento = departamentoRepositories.findById(muniDto.getDepaId())
        .orElseThrow(() -> new EntityNotFoundException("Departamento no encontrado"));
        muni.setDepartamento(departamento);
        return muni;
    }

    @Override
    public MuniDto toMuniDto(Muni muni){
        return new MuniDto(
            muni.getId_muni(),
            muni.getNom(),
            muni.getDepartamento().getId_Dep());
    }
    @Override
    public List<MuniDto> toMuniDtoList(List<Muni>munis){
        if(munis==null){
            return null;
        }
        List<MuniDto>muniDtos=new ArrayList<MuniDto>(munis.size());
        for(Muni muni : munis){
            muniDtos.add(toMuniDto(muni));
        }
        return muniDtos;
    }
    @Override
    public void updateMuni(Muni muni,MuniDto muniDto){
        if(muniDto == null){
            return;
        }
        muni.setId_muni(muniDto.getId_munic());
        muni.setNom(muniDto.getNombr());
    }
}
