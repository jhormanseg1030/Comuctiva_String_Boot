package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.Pedi_ProducDto;
import com.comuctiva.comuctiva.Dto.PedidosAsignadosDto;
import com.comuctiva.comuctiva.Dto.ProductoAsignado2Dto;
import com.comuctiva.comuctiva.models.Pedi_Produc;
import com.comuctiva.comuctiva.models.Pedi_producId;
import com.comuctiva.comuctiva.models.Pedidos;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.repositoryes.PedidoRepositorie;
import com.comuctiva.comuctiva.repositoryes.ProductoRepositorie;

import jakarta.persistence.EntityNotFoundException;

@Component
public class Pedi_ProducMapperImple implements Pedi_ProducMapper {

    private final PedidoRepositorie pedidoRepositorie;
    private final ProductoRepositorie productoRepositorie;
    
    public Pedi_ProducMapperImple(PedidoRepositorie pedidoRepositorie, ProductoRepositorie productoRepositorie ){
        this.pedidoRepositorie = pedidoRepositorie;
        this.productoRepositorie = productoRepositorie;
    }

    @Override
    public Pedi_Produc toPedi_Produc(Pedi_ProducDto pedi_ProducDto){

        Pedidos pedido = pedidoRepositorie.findById(pedi_ProducDto.getId_pedido())
        .orElseThrow(() -> new EntityNotFoundException("pedido no encontrado con el id"));

        Producto producto = productoRepositorie.findById(pedi_ProducDto.getId_producto())
        .orElseThrow(()-> new EntityNotFoundException("Producto no encontrada con el id"));

        Pedi_producId id = new Pedi_producId();
            id.setPedidosId(pedi_ProducDto.getId_pedido());
            id.setProId(pedi_ProducDto.getId_producto());
        
        Pedi_Produc pp = new Pedi_Produc();
        pp.setId(id);
        pp.setCant(pedi_ProducDto.getCantidad());
        pp.setValor(pedi_ProducDto.getValor());
        pp.setPedi(pedido);
        pp.setProductos(producto);
        return pp;
    }

    @Override
    public Pedi_ProducDto toPedi_ProducDto(Pedi_Produc pediProduc) {
        Pedi_ProducDto dto = new Pedi_ProducDto();
        dto.setId_pedido(pediProduc.getId().getPedidosId());
        dto.setId_producto(pediProduc.getId().getProId());
        dto.setNombre_del_producto(pediProduc.getProductos().getNomprod());
        dto.setCantidad(pediProduc.getCant());
        dto.setValor(pediProduc.getValor());
        return dto;
    }
    
    @Override
    public PedidosAsignadosDto toPedi_AsigDto(Pedi_Produc pedi){
        PedidosAsignadosDto dto =  new PedidosAsignadosDto();
        dto.setId_pedido(pedi.getPedi().getId_pedido());
        dto.setCantidad(pedi.getCant());
        dto.setValor(pedi.getValor());
        return dto;
    }

    @Override
    public ProductoAsignado2Dto toProd_Asig(Pedi_Produc prod){
        ProductoAsignado2Dto dto = new ProductoAsignado2Dto();
        dto.setNombre_del_producto(prod.getProductos().getNomprod());
        dto.setCantidad(prod.getCant());
        dto.setValor(prod.getValor());
        return dto;
    }
}
