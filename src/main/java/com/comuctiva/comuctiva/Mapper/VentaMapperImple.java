package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.VentaDto;
import com.comuctiva.comuctiva.models.Comp_Produc;

@Component
public class VentaMapperImple implements VentaMapper {

    @Override
    public VentaDto toVentaDto(Comp_Produc comp_produc) {
        if (comp_produc == null) {
            return null;
        }
        
        return new VentaDto(
            comp_produc.getCompra().getId_compra(),
            comp_produc.getCompra().getFec_com(),
            comp_produc.getCompra().getTotal(),
            comp_produc.getCompra().getPedido().getUsuario().getNom_Usu(), 
            comp_produc.getProducto().getNomprod(),
            comp_produc.getCantidad(),
            comp_produc.getPrecio()
        );
    }
}
