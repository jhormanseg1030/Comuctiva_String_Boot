package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.PedidosDto;
import com.comuctiva.comuctiva.Mapper.PedidosMapper;
import com.comuctiva.comuctiva.models.Pedidos;
import com.comuctiva.comuctiva.repositoryes.PedidoRepositorie;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidosServicisImple implements PedidosServices {

private final PedidoRepositorie pedidoRepositorie;
    private final PedidosMapper pedidosMapper;

    public PedidosServicisImple(PedidoRepositorie pedidosRepositories, PedidosMapper pedidosMapper) {
        this.pedidoRepositorie = pedidosRepositories;
        this.pedidosMapper = pedidosMapper;
    }
    @Override
    public PedidosDto crearPedidos(PedidosDto pedidosDto) {
        Pedidos pedidos = pedidosMapper.toPedidos(pedidosDto);
        Pedidos pedidosGuardado = pedidoRepositorie.save(pedidos);
        return pedidosMapper.toPedidosDto(pedidosGuardado);
    }

    @Override
    public PedidosDto pedidosPorId(Integer id){
        return pedidoRepositorie.findById(id)
        .map(pedidosMapper :: toPedidosDto)
        .orElseThrow(()-> new EntityNotFoundException("Pedido no encontrado contrado"));
    }

    @Override
    public List<PedidosDto>listartodos(){
        return pedidoRepositorie.findAll()
        .stream()
        .map(pedidosMapper::toPedidosDto)
        .collect(Collectors.toList());
    }

    @Override
    public void eliminarPedidos(Integer id){
        pedidoRepositorie.deleteById(id);
    }
}
