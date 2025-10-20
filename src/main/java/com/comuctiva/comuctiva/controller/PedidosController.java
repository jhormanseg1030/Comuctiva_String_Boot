package com.comuctiva.comuctiva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.PedidoCreateDto;
import com.comuctiva.comuctiva.Dto.PedidoUpdateDto;
import com.comuctiva.comuctiva.Dto.PedidosDto;
import com.comuctiva.comuctiva.services.PedidosServices;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/pedidos")
public class PedidosController {
    private final PedidosServices pedidosServices;

    public PedidosController(PedidosServices pedidosServices) {
        this.pedidosServices = pedidosServices;
    }

    //Crear un pedido
    @PostMapping
    public ResponseEntity<?> crearPedido( @Valid @RequestBody PedidoCreateDto pedidoCreateDto){
        try{
                PedidosDto pedidosDto = pedidosServices.crearPedidos(pedidoCreateDto);
                return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("mensaje", "Pedido creado con exito", "pedidos", pedidosDto));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("errores1", ex.getMessage()));
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error","error al crear un pedido", "detalles", ex.getMessage()));
        }
    }

    //consultar pedidos por id
    @GetMapping("/{id}")
    public ResponseEntity<PedidosDto> consultarPorId(@PathVariable Integer id_pedido){
        PedidosDto pedidos = pedidosServices.pedidosPorId(id_pedido);
        return ResponseEntity.ok(pedidos);
    }

    //listar pedidos solo del usuario autenticado
    @GetMapping
    public ResponseEntity<List<PedidosDto>> listartodos(Authentication authentication){
        String documento = authentication.getName();
        List<PedidosDto> pedidos = pedidosServices.listarPorDocumentoVendedor(documento);
        return ResponseEntity.ok(pedidos);
    }

    //actualizar pedido
    @PutMapping("/{id}")
    public ResponseEntity<PedidosDto> actualizarPedido(@PathVariable Integer id_pedido,
    @Valid @RequestBody PedidoUpdateDto pediActualizadoDto){
        pediActualizadoDto.setEstId(id_pedido);
        PedidosDto pedidosActualizado = pedidosServices.actualizarPedidos(pediActualizadoDto);
        return ResponseEntity.ok(pedidosActualizado);
    }
}

