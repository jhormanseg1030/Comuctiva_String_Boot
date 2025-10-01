package com.comuctiva.comuctiva.Mapper;

// import java.util.List;

import com.comuctiva.comuctiva.Dto.PedidoCreateDto;
import com.comuctiva.comuctiva.Dto.PedidosDto;
import com.comuctiva.comuctiva.models.Pedidos;

public interface PedidosMapper {

    Pedidos toPedidos(PedidoCreateDto pediCreDto);

    PedidosDto toPedidosDto(Pedidos pedidos);
}
