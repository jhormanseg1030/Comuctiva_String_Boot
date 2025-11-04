package com.comuctiva.comuctiva.controller;

import com.comuctiva.comuctiva.Dto.*;
import com.comuctiva.comuctiva.models.EstadoCotizacion;
import com.comuctiva.comuctiva.services.CotizacionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {
    
    private final CotizacionService cotizacionService;
    
    public CotizacionController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }
    
    @PostMapping("/calcular")
    public ResponseEntity<?> calcular(@Valid @RequestBody CotizacionCalculoRequest request) {
        try {
            CotizacionCalculoResponse respuesta = cotizacionService.calcular(request);
            
            if (respuesta.getTotal() == 0.0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", respuesta.getMensaje()));
            }
            
            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al calcular cotización", "detalles", e.getMessage()));
        }
    }
    
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody CotizacionCreateDto createDto) {
        try {
            CotizacionDto cotizacion = cotizacionService.crear(createDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje", "Cotización creada exitosamente", "cotizacion", cotizacion));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear cotización", "detalles", e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<List<CotizacionDto>> listar(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestParam(required = false) EstadoCotizacion estado) {
        
        List<CotizacionDto> cotizaciones = cotizacionService.listarConFiltros(from, to, estado);
        return ResponseEntity.ok(cotizaciones);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            CotizacionDto cotizacion = cotizacionService.buscarPorId(id);
            return ResponseEntity.ok(cotizacion);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id, @Valid @RequestBody CotizacionEstadoDto estadoDto) {
        try {
            CotizacionDto cotizacion = cotizacionService.cambiarEstado(id, estadoDto);
            return ResponseEntity.ok(Map.of("mensaje", "Estado actualizado exitosamente", "cotizacion", cotizacion));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al cambiar estado", "detalles", e.getMessage()));
        }
    }
}
