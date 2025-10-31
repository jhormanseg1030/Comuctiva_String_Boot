package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.PedidoCreateDto;
import com.comuctiva.comuctiva.Dto.PedidoUpdateDto;
import com.comuctiva.comuctiva.Dto.PedidosDto;

public interface PedidosServices {

    PedidosDto crearPedidos(PedidoCreateDto pedidoCreateDto);

    PedidosDto pedidosPorId(Integer id);

    List<PedidosDto> listartodos();

    void eliminarPedidos(Integer id);

    PedidosDto actualizarPedidos(PedidoUpdateDto pedidoUpdateDto);
}