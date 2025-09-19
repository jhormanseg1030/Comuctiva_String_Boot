package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.PedidoCreateDto;
import com.comuctiva.comuctiva.Dto.PedidoUpdateDto;
import com.comuctiva.comuctiva.Dto.PedidosDto;
import com.comuctiva.comuctiva.Mapper.PedidosMapper;
import com.comuctiva.comuctiva.models.Pedidos;
import com.comuctiva.comuctiva.repositoryes.EstadoRepositories;
import com.comuctiva.comuctiva.repositoryes.Guia_EnvioRepositories;
import com.comuctiva.comuctiva.repositoryes.PedidoRepositorie;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PedidosServicisImple implements PedidosServices {

    private final PedidoRepositorie pedidoRepositorie;
    private final PedidosMapper pedidosMapper;
    private final UsuarioRepositories usuarioRepositories;
    private final Guia_EnvioRepositories guia_EnvioRepositories;
    private final EstadoRepositories estadoRepositories;

    public PedidosServicisImple(PedidoRepositorie pedidosRepositories, PedidosMapper pedidosMapper, UsuarioRepositories usuarioRepositories, Guia_EnvioRepositories guia_EnvioRepositories, EstadoRepositories estadoRepositories) {
        this.pedidoRepositorie = pedidosRepositories;
        this.pedidosMapper = pedidosMapper;
        this.usuarioRepositories = usuarioRepositories;
        this.guia_EnvioRepositories = guia_EnvioRepositories;
        this.estadoRepositories = estadoRepositories;
    }
    @Override
    @Transactional
    public PedidosDto crearPedidos(PedidoCreateDto pedidoCreateDto) {
        Pedidos pedidos = pedidosMapper.toPedidos(pedidoCreateDto);
        Pedidos pedidosGuardado = pedidoRepositorie.save(pedidos);
        return pedidosMapper.toPedidosDto(pedidosGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public PedidosDto pedidosPorId(Integer id_pe){
        Pedidos pedidos = pedidoRepositorie.findById(id_pe)
        .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
        return pedidosMapper.toPedidosDto(pedidos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidosDto>listartodos(){
        return pedidoRepositorie.findAll()
        .stream()
        .map(pedidosMapper::toPedidosDto)
        .collect(Collectors.toList());
    }

    @Override
    public void eliminarPedidos(Integer id_pe){
        Pedidos pedidos = pedidoRepositorie.findById(id_pe)
        .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
        pedidoRepositorie.delete(pedidos);
    }
    @Override
    @Transactional
    public PedidosDto actualizarPedidos(PedidoUpdateDto pedidoUpdateDto){
        Pedidos pedidos = pedidoRepositorie.findById(pedidoUpdateDto.getId_pedi())
        .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));

        pedidos.setFehor_pedi(pedidoUpdateDto.getFechor_pedi());
    }
}
