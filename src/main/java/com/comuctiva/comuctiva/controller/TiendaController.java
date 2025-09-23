package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.TiendaCreateDto;
import com.comuctiva.comuctiva.Dto.TiendaDto;
import com.comuctiva.comuctiva.Dto.TiendaUpdateDto;
import com.comuctiva.comuctiva.services.TiendaServices;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/tienda")
public class TiendaController {
    private final TiendaServices tiendaServices;

    public TiendaController(TiendaServices tiendaServices) {
        this.tiendaServices = tiendaServices;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearTienda(@Valid @RequestBody TiendaCreateDto tiendaCreateDto) {
        try{
            TiendaDto tienda = tiendaServices.crearTienda(tiendaCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of("mensaje", "Tienda creada con éxito", "detalles", tienda));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("mensaje",ex.getMessage()));
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("mensaje", "Error al crear una tienda", "detalles", ex.getMessage()));
        }
    }
    
    @GetMapping("/{id_ti}")
    public ResponseEntity<TiendaDto> obtenerId(@PathVariable Integer id_ti) {
        TiendaDto tienda = tiendaServices.tiendaPorId(id_ti);
        return ResponseEntity.ok(tienda);
    }
    
    @GetMapping
    public ResponseEntity<List<TiendaDto>> listar() {
        List<TiendaDto> tienda = tiendaServices.listartodos();
        return ResponseEntity.ok(tienda);
    }

    @PutMapping("/{id_ti}")
    public ResponseEntity<TiendaDto> putActualizar(@PathVariable Integer id_ti, @RequestBody TiendaUpdateDto tiendaUpdate) {
        tiendaUpdate.setId_ti(id_ti);
        TiendaDto actualizado = tiendaServices.actualizarTienda(tiendaUpdate);
        return ResponseEntity.ok(actualizado);
    }
}
