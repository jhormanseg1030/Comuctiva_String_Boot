package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.BarrioDto;
import com.comuctiva.comuctiva.models.Barr_Vere;
import com.comuctiva.comuctiva.models.Barrio;
import com.comuctiva.comuctiva.models.Muni;
import com.comuctiva.comuctiva.repositoryes.Barr_VereRepositories;
import com.comuctiva.comuctiva.repositoryes.MuniRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class BarrioMapperImple implements BarrioMapper{

    private final Barr_VereRepositories barr_VereRepositories;
    private final MuniRepositories muniRepositories;

    public BarrioMapperImple(Barr_VereRepositories barr_VereRepositories,MuniRepositories muniRepositories){
        this.barr_VereRepositories=barr_VereRepositories;
        this.muniRepositories=muniRepositories;
    }

    @Override
    public Barrio toBarrio(BarrioDto barrioDto){
        Barrio barrio = new Barrio();
        barrio.setId_barrio(barrioDto.getId_barr());
        barrio.setNom(barrioDto.getNomb());

        Barr_Vere barr_Vere = barr_VereRepositories.findById(barrioDto.getBarr_verId())
        .orElseThrow(()-> new EntityNotFoundException("Barrio no encontrado con id:"));
        barrio.setBarr_Vere(barr_Vere);

        Muni muni = muniRepositories.findById(barrioDto.getMuniId())
        .orElseThrow(()-> new EntityNotFoundException("Municipio no encontrado con id:"));
        barrio.setMuni(muni);
        return barrio;
    }

    @Override
    public BarrioDto toBarrioDto(Barrio barrio){
        return new BarrioDto(
            barrio.getId_barrio(),
            barrio.getNom(),
            barrio.getBarr_Vere().getId_vere(),
            barrio.getMuni().getId_muni());
    }
    @Override
    public List<BarrioDto> toBarrioDtoList(List<Barrio>barrioss){
        if (barrioss ==null){
            return List.of();
        }
        List <BarrioDto>barrioDtos=new ArrayList<BarrioDto>(barrioss.size());
        for(Barrio barrio : barrioss){
            barrioDtos.add(toBarrioDto(barrio));
        }
        return barrioDtos;
    }
    @Override
    public void updateBarrio(Barrio barrio, BarrioDto barrioDto){
        if (barrioDto == null){
            return;
        }
        barrio.setId_barrio((barrioDto.getId_barr()));
        barrio.setNom(barrioDto.getNomb());

    }
}