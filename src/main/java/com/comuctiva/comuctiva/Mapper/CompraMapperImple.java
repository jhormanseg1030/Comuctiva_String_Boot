    package com.comuctiva.comuctiva.Mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.Dto.CompraCreateDto;
import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.models.Comp_Produc;
import com.comuctiva.comuctiva.models.Compra;

    @Component
    public class CompraMapperImple implements CompraMapper {

    @Override
    public Compra toCompra(CompraCreateDto compraCreateDto) {
        if (compraCreateDto == null) return null;
        
        Compra compra = new Compra();
        compra.setRef_pago(compraCreateDto.getRef_pago());
        compra.setFec_com(java.time.LocalDateTime.now());
        compra.setTotal(0.0); // Se calcula después
        
        return compra;
    }

    @Override
    public CompraDto toCompraDto(Compra compra) {
        if (compra == null) return null;
        
        CompraDto dto = new CompraDto();
        dto.setId_compra(compra.getId_compra());
        dto.setTotal(compra.getTotal());
        dto.setRef_pago(compra.getRef_pago());
        dto.setFec_com(compra.getFec_com());
        dto.setId_pedido(compra.getPedido() != null ? compra.getPedido().getId_pedido() : null);
        dto.setId_ti_pago(compra.getTipo_pago() != null ? compra.getTipo_pago().getId_tipago() : null);
        dto.setTipo_pago(compra.getTipo_pago() != null ? compra.getTipo_pago().getTipos() : null);
        
        if (compra.getProductos() != null) {
            dto.setProductos(compra.getProductos().stream()
                .map(this::toCompraProductoDto)
                .collect(Collectors.toList()));
        }
        return dto;
    }

    
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
    }
