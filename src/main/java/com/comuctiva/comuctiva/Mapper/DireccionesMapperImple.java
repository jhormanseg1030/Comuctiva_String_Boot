 /*package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.DireccionesDto;
import com.comuctiva.comuctiva.models.Direcciones;

@Component
public class DireccionesMapperImple implements DireccionesMapper {

   @Override
    public Direcciones toDirecciones( DireccionesDto direccionesDto){
        if(direccionesDto == null){
            return null;
        }
        Direcciones direcciones = new Direcciones();
        direcciones.setId_direcc(direccionesDto.getId_direc());
        direcciones.setComple(direccionesDto.getCompl());
        direcciones.setNum(direccionesDto.getNume());
        direcciones.setUbi_geo(direccionesDto.getUbic_geo());
        return direcciones;
    }

    @Override
    public DireccionesDto toDireccionesDto(Direcciones  direcciones){
        if (direcciones == null) {
        return null;
        }
        DireccionesDto direccionesDto = new  DireccionesDto();
        direccionesDto.setId_direc(direcciones.getId_direcc());
        direccionesDto.setCompl(direcciones.getComple());
        direccionesDto.setNume(direcciones.getNum());
        direccionesDto.setUbic_geo(direcciones.getUbi_geo());
        return direccionesDto;
    }

    @Override
    public List<DireccionesDto> toDireccionesDtoList(List<Direcciones>direccioness){
        if (direccioness == null) {
            return List.of();
        }
        List<DireccionesDto>direccionesDtos=new ArrayList<DireccionesDto>(direccioness.size());
        for(Direcciones direcciones : direccioness){
            direccionesDtos.add(toDireccionesDto(direcciones));
        }
        return direccionesDtos;
    }
    @Override
    public void updateDirecciones(Direcciones direcciones, DireccionesDto direccionesDto){
        if(direccionesDto == null){
            return;
        }
        direcciones.setId_direcc(direccionesDto.getId_direc());
        direcciones.setComple(direccionesDto.getCompl());
        direcciones.setNum(direccionesDto.getNume());
        direcciones.setUbi_geo(direccionesDto.getUbic_geo());
    }


}
*/