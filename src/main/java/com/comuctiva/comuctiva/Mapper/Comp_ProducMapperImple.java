package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.Com_ProducCreateDto;
import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.models.Comp_Produc;

@Component
public class Comp_ProducMapperImple implements Comp_producMapper {

        @Override
    public Com_ProducDto toCompraProductoDto(Comp_Produc comp_produc) {
        if (comp_produc == null) return null;
        
        Com_ProducDto dto = new Com_ProducDto();
        dto.setId_com_produc(comp_produc.getId_comp_produc());
        dto.setId_producto(comp_produc.getProducto() != null ? comp_produc.getProducto().getId_producto() : null);
        dto.setNombre_producto(comp_produc.getProducto() != null ? comp_produc.getProducto().getNomprod() : null);
        dto.setCantidad(comp_produc.getCantidad());
        dto.setPrecio(comp_produc.getPrecio());
        dto.setSubtotal(comp_produc.getCantidad() * comp_produc.getPrecio());
        
        return dto;
    }

        @Override
    public Comp_Produc toCompProducto(Com_ProducCreateDto compraProductoCreateDto) {
        if (compraProductoCreateDto == null) return null;
        
        Comp_Produc comp_produc = new Comp_Produc();
        comp_produc.setCantidad(compraProductoCreateDto.getCantidad());
        comp_produc.setPrecio(compraProductoCreateDto.getPrecio());
        
        return comp_produc;
    }
}
