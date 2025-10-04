package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.Ingres_ProducDto;
import com.comuctiva.comuctiva.Dto.IngresosAsigDto;
import com.comuctiva.comuctiva.Dto.ProducAsig4Dto;
import com.comuctiva.comuctiva.models.Ingres_Produc;
import com.comuctiva.comuctiva.models.Ingres_ProducId;
import com.comuctiva.comuctiva.models.Ingresos;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.repositoryes.IngresosRepositories;
import com.comuctiva.comuctiva.repositoryes.ProductoRepositorie;

import jakarta.persistence.EntityNotFoundException;

@Component
public class Ingres_producMapperImple implements Ingres_ProducMapper {

    private final IngresosRepositories ingresosRepositories;
    private final ProductoRepositorie productoRepositorie;

    public Ingres_producMapperImple(IngresosRepositories ingresosRepositories, ProductoRepositorie productoRepositorie) {
        this.ingresosRepositories = ingresosRepositories;
        this.productoRepositorie = productoRepositorie;
    }

    @Override
    public Ingres_Produc toIngres_ProducDto(Ingres_ProducDto ingresDto){

        Ingresos ingresos = ingresosRepositories.findById(ingresDto.getId_ingresos())
        .orElseThrow(()-> new EntityNotFoundException("No se encontro el ingresos con id "));

        Producto producto = productoRepositorie.findById(ingresDto.getId_Producto())
        .orElseThrow(()-> new EntityNotFoundException("No se encontro el producto con id "));
        Ingres_ProducId id = new Ingres_ProducId(ingresDto.getId_ingresos(),ingresDto.getId_Producto());
    
        Ingres_Produc ip = new Ingres_Produc();
        ip.setId(id);
        ip.setCant(ingresDto.getCantidad());
        ip.setIngresos(ingresos);
        ip.setProducto(producto);
        return ip;
    }
    
    @Override
    public Ingres_ProducDto toIngresProDto(Ingres_Produc ingres_Produc){
        return new Ingres_ProducDto(
            ingres_Produc.getIngresos().getId_ingreso(),
            ingres_Produc.getProducto().getId_producto(),
            ingres_Produc.getProducto().getNomprod(),
            ingres_Produc.getCant()
        );
    }

    @Override
    public IngresosAsigDto toIngresAsig(Ingres_Produc asigIngre){
        IngresosAsigDto dto = new IngresosAsigDto();
        dto.setId_ingresos(asigIngre.getIngresos().getId_ingreso());
        dto.setCantidad(asigIngre.getCant());
        return dto;
    }

    @Override
    public ProducAsig4Dto toproAsig(Ingres_Produc asigPro){
        ProducAsig4Dto dto = new ProducAsig4Dto();
        dto.setCantidad(asigPro.getCant());
        dto.setNombre_producto(asigPro.getProducto().getNomprod());
        return dto;
    }
}
