package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.PedidoCreateDto;
import com.comuctiva.comuctiva.Dto.PedidosDto;
import com.comuctiva.comuctiva.models.Estado;
import com.comuctiva.comuctiva.models.Guia_Envio;
import com.comuctiva.comuctiva.models.Pedidos;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.EstadoRepositories;
import com.comuctiva.comuctiva.repositoryes.Guia_EnvioRepositories;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class PedidosMapperImple implements PedidosMapper{

    private final UsuarioRepositories usuarioRepositories;
    private final Guia_EnvioRepositories guia_EnvioRepositories;
    private final EstadoRepositories estadoRepositories;

    public PedidosMapperImple(UsuarioRepositories usuarioRepositories, Guia_EnvioRepositories guia_EnvioRepositories, EstadoRepositories estadoRepositories){
        this.usuarioRepositories=usuarioRepositories;
        this.guia_EnvioRepositories=guia_EnvioRepositories;
        this.estadoRepositories=estadoRepositories;
    }

    @Override
    public Pedidos toPedidos(PedidoCreateDto pediCreDto){
        if(pediCreDto == null)
        return null;

        Pedidos pedidos = new Pedidos();
        pedidos.setFehor_pedi(pediCreDto.getFechor_pedi());

        Usuario usuario = usuarioRepositories.findById(pediCreDto.getUsuarioId())
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        pedidos.setUsuario(usuario);

        Guia_Envio guia_Envio = guia_EnvioRepositories.findById(pediCreDto.getGuiaenviId())
        .orElseThrow(() -> new EntityNotFoundException("Guia de envio no encontrada"));
        pedidos.setGuia_envio(guia_Envio);

        Estado estado = estadoRepositories.findById(pediCreDto.getEstadoId())
        .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado"));
        pedidos.setEstado(estado);
        return pedidos;
    }

    @Override
    public PedidosDto toPedidosDto(Pedidos pedidos){
        return new PedidosDto(
            pedidos.getId_pedido(),
            pedidos.getFehor_pedi(),
            pedidos.getUsuario() != null ? pedidos.getUsuario().getId_Usuario() : null,
            pedidos.getGuia_envio() != null ? pedidos.getGuia_envio().getId_guia() : null,
            pedidos.getEstado() !=null ? pedidos.getEstado().getId_estado() : null);
    }

    // @Override
    // public List<PedidosDto> toPedidosDtoList(List<Pedidos>pedi){
    //     if(pedi== null){
    //         return List.of();
    //     }
    //     List<PedidosDto>pediDtos = new ArrayList<PedidosDto>(pedi.size());
    //     for(Pedidos pedidos : pedi){
    //         pediDtos.add(toPedidosDto(pedidos));
    //     }
    //     return pediDtos;
    // }
    // @Override
    // public void updatePedidos(Pedidos pedidos, PedidosDto pedidosDto){
    //     if (pedidosDto==null) {
    //         return;
    //     }
    //     pedidos.setId_pedido(pedidosDto.getId_pe());
    //     pedidos.setFehor_pedi(pedidosDto.getFh_p());
    // }
}
