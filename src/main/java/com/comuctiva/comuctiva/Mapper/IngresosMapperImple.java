package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.IngresosDto;
import com.comuctiva.comuctiva.models.Ingresos;

@Component
public class IngresosMapperImple implements IngresosMapper{

    @Override
    public Ingresos toIngresos (IngresosDto ingresosDto){
        if (ingresosDto== null) {
            return null;
        }
        Ingresos ingresos = new Ingresos();
        ingresos.setId_ingreso(ingresosDto.getId_ingre());
        ingresos.setFecha(ingresosDto.getFech());
        ingresos.setOrbser(ingresosDto.getObser());
        return ingresos;
    }

    @Override
    public IngresosDto toIngresosDto(Ingresos ingresos){
        if (ingresos== null) {
        return null;
        }
        IngresosDto ingresosDto = new IngresosDto();
        ingresosDto.setId_ingre(ingresos.getId_ingreso());
        ingresosDto.setFech(ingresos.getFecha());
        ingresosDto.setObser(ingresos.getOrbser());
        return ingresosDto;
    }
@Override
public List<IngresosDto>toIngresosDtoList(List<Ingresos>ingresoss){
    if(ingresoss==null){
        return List.of();
    }
    List<IngresosDto> ingresosDtos = new ArrayList<IngresosDto>(ingresoss.size());
    for(Ingresos ingresos: ingresoss){
    ingresosDtos.add(toIngresosDto(ingresos));
    }
    return ingresosDtos;
}
@Override
public void updateIngresos(Ingresos ingresos,IngresosDto ingresosDto){
    if(ingresosDto == null){
        return;
    }
    ingresos.setId_ingreso(ingresosDto.getId_ingre());
    ingresos.setOrbser(ingresosDto.getObser());
    ingresos.setFecha(ingresos.getFecha());
}
}
