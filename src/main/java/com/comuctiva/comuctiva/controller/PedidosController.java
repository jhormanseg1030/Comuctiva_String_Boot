package com.comuctiva.comuctiva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.PedidosDto;
import com.comuctiva.comuctiva.services.PedidosServices;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/pedidos")
public class PedidosController {
    private final PedidosServices pedidosServices;

    public PedidosController(PedidosServices pedidosServices) {
        this.pedidosServices = pedidosServices;
    }

    @PostMapping("/crearemos")
    public ResponseEntity<PedidosDto> crearPedido( @Valid @RequestBody PedidosDto pedidosDto){
        PedidosDto nuevoPedido = pedidosServices.crearPedidos(pedidosDto);
        return ResponseEntity.ok(nuevoPedido);
    }
    
}

