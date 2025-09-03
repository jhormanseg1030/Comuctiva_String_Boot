/*package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.IngresosDto;
import com.comuctiva.comuctiva.models.Ingresos;

@Component
public class IngresosMapperImple implements IngresosMapper{

@Override
public 

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
    ingresos.setId_ingresos(ingresosDto.getId_ingre());
    ingresos.setOrbser(ingresosDto.getObser());
    ingresos.setFecha(ingresos.getFecha());
}
}
*/