package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.PedidosDto;

public interface PedidosServices {

    PedidosDto crearPedidos(PedidosDto pedidosDto);

    PedidosDto pedidosPorId(Integer id);

    List<PedidosDto> listartodos();

    void eliminarPedidos(Integer id);
}