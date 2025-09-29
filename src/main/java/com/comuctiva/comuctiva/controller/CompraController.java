package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.CompraCreateDto;
import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.Dto.CompraUpdateDto;
import com.comuctiva.comuctiva.services.CompraServices;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/compras")
public class CompraController {
        
    private final CompraServices compraServices;

    public CompraController(CompraServices compraServices) {
        this.compraServices = compraServices;
    }

    @PostMapping
    public ResponseEntity<?> realizarCompra(@Valid @RequestBody CompraCreateDto compraCreateDto) {
        try{
            CompraDto compra = compraServices.crearCompra(compraCreateDto);
            return ResponseEntity.status(201)
            .body(Map.of("mensaje", "Compra creada con éxito", "detalles", compra));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(400)
            .body(Map.of("mensaje",ex.getMessage()));
        }catch(Exception ex){
            return ResponseEntity.status(500)
            .body(Map.of("mensaje", "Error al crear una compra", "detalles", ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraDto> obtenerCompra(@RequestParam Integer id) {
        CompraDto compra = compraServices.compraPorId(id);
        return ResponseEntity.ok(compra);
    }
    
    @GetMapping
    public ResponseEntity<List<CompraDto>> listar() {
        List<CompraDto> compras = compraServices.listartodos();
        return ResponseEntity.ok(compras);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CompraDto> putActualizar(@PathVariable Integer id, @RequestBody CompraUpdateDto compraUpdate) {
        compraUpdate.setId_compra(id);
        CompraDto actualizado = compraServices.actualizarCompra(compraUpdate);
        return ResponseEntity.ok(actualizado);
    }
}

