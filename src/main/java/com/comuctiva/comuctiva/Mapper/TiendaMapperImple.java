package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;
import com.comuctiva.comuctiva.Dto.TiendaCreateDto;
import com.comuctiva.comuctiva.Dto.TiendaDto;
import com.comuctiva.comuctiva.models.Direcciones;
import com.comuctiva.comuctiva.models.Tienda;
import com.comuctiva.comuctiva.repositoryes.DireccionesRepositories;
import jakarta.persistence.EntityNotFoundException;

@Component
public class TiendaMapperImple implements TiendaMapper {

    private final DireccionesRepositories direccionesRepositories;
    
    public TiendaMapperImple(DireccionesRepositories direccionesRepositories){
        this.direccionesRepositories=direccionesRepositories;
    }

    @Override
    public Tienda toTienda(TiendaCreateDto tiendaCreateDto){
        if (tiendaCreateDto == null) {
            return null;
        }
            Tienda tienda = new Tienda();
            tienda.setNombreT(tiendaCreateDto.getNomti());
            tienda.setLogo(tiendaCreateDto.getLoogo());

            Direcciones direcciones = direccionesRepositories.findById(tiendaCreateDto.getDireccId())
            .orElseThrow(()-> new EntityNotFoundException("Direcciones no encontrado con id:"));
            tienda.setDirecciones(direcciones);
            return tienda;
        }
    
    @Override
    public TiendaDto toTiendaDto(Tienda tienda) {
        return new TiendaDto(
            tienda.getID_Tienda(),
            tienda.getNombreT(),
            tienda.getLogo(),
            tienda.getDirecciones().getId_direcc());
    }
}
