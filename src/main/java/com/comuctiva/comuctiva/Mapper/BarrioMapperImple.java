package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.BarrioDto;
import com.comuctiva.comuctiva.models.Barrio;

@Component
public class BarrioMapperImple implements BarrioMapper{
    @Override
    public Barrio toBarrio(BarrioDto barrioDto){
        if (barrioDto==null){
            return null;
        }
        Barrio barrio=new Barrio();
        barrio.setId_barrio(barrioDto.getId_barr());
        barrio.setNom(barrioDto.getNomb());
        return barrio;
    }
    @Override
    public BarrioDto toBarrioDto(Barrio barrio){
        if (barrio == null){
            return null;
        }
        BarrioDto barrioDto = new BarrioDto();
        barrioDto.setId_barr(barrio.getId_barrio());
        barrioDto.setNomb(barrio.getNom());
        return barrioDto;
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