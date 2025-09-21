package com.comuctiva.comuctiva.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.PedidoCreateDto;
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
    public ResponseEntity<?> crearPedido( @Valid @RequestBody PedidoCreateDto pedidoCreateDto){
        try{
                PedidosDto pedidosDto = pedidosServices.crearPedidos(pedidoCreateDto);
                return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("mensaje", "Pedido creado con exito", "pedidos", pedidosDto));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("mensaje", ex.getMessage()));
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error","error al crear un pedido", "detalles", ex.getMessage()));
        }
    }
}

