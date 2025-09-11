package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.PedidosDto;
import com.comuctiva.comuctiva.models.Pedidos;

public interface PedidosMapper {
    Pedidos toPedidos(PedidosDto pedidosDto);

    PedidosDto toPedidosDto(Pedidos pedidos);

    List<PedidosDto>toPedidosDtoList(List<Pedidos>pedi);
    
    void updatePedidos(Pedidos pedidos, PedidosDto pedidosDto);
}
