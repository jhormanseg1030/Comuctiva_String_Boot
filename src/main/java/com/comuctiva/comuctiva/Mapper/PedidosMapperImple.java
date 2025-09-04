package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.PedidosDto;
import com.comuctiva.comuctiva.models.Pedidos;

@Component
public class PedidosMapperImple implements PedidosMapper{

    @Override
    public Pedidos toPedidos(PedidosDto pedidosDto){
        if(pedidosDto == null){
            return null;
        }
    Pedidos pedidos = new Pedidos();
    pedidos.setId_pedido(pedidosDto.getId_pe());
    pedidos.setFehor_pedi(pedidosDto.getFh_p());
    return pedidos;
    }
    @Override
    public PedidosDto toPedidosDto(Pedidos pedidos){
        if (pedidos==null) {
            return null;
        }
        PedidosDto pedidosDto = new PedidosDto();
        pedidosDto.setId_pe(pedidos.getId_pedido());
        pedidosDto.setFh_p(pedidos.getFehor_pedi());
        return pedidosDto;
    }
    @Override
    public List<PedidosDto> toPedidosDtoList(List<Pedidos>pedi){
        if(pedi== null){
            return List.of();
        }
        List<PedidosDto>pediDtos = new ArrayList<PedidosDto>(pedi.size());
        for(Pedidos pedidos : pedi){
            pediDtos.add(toPedidosDto(pedidos));
        }
        return pediDtos;
    }
    @Override
    public void updatePedidos(Pedidos pedidos, PedidosDto pedidosDto){
        if (pedidosDto==null) {
            return;
        }
        pedidos.setId_pedido(pedidosDto.getId_pe());
        pedidos.setFehor_pedi(pedidosDto.getFh_p());
    }
}
