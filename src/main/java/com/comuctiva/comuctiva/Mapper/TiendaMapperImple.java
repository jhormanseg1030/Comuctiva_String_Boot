package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.TiendaDto;
import com.comuctiva.comuctiva.models.Tienda;

@Component
public class TiendaMapperImple implements TiendaMapper {
@Override
public Tienda toTienda(TiendaDto tiendaDto){
    if (tiendaDto== null) {
        return null;
    }
    Tienda tienda= new Tienda();
    tienda.setID_Tienda(tiendaDto.getId_ti());
    tienda.setNombreT(tiendaDto.getNomti());
    tienda.setLog(tiendaDto.getLoogo());
    return tienda;
}
@Override
public TiendaDto toTiendaDto(Tienda tienda){
    if (tienda == null){
        return null;
    }
    TiendaDto tiendaDto= new TiendaDto();
    tiendaDto.setId_ti(tienda.getID_Tienda());
    tiendaDto.setNomti(tienda.getNombreT());
    tiendaDto.setLoogo(tienda.getLog());
    return tiendaDto;
}
@Override
public List<TiendaDto> toTiendaDtoList(List<Tienda>tien){
    if(tien == null){
        return List.of();
    }
List<TiendaDto> tiendaDtos=new ArrayList<TiendaDto>(tien.size());
for(Tienda tienda: tien){
    tiendaDtos.add(toTiendaDto(tienda));
}
return tiendaDtos;
}
@Override
public void updateTienda(Tienda tienda, TiendaDto tiendaDto){
    if (tiendaDto == null) {
        return;
    }
    tienda.setID_Tienda(tiendaDto.getId_ti());
    tienda.setNombreT(tiendaDto.getNomti());
    tienda.setLog(tiendaDto.getLoogo());
}
}