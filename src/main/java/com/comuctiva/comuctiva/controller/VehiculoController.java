package com.comuctiva.comuctiva.controller;

import com.comuctiva.comuctiva.Dto.VehiculoCreateDto;
import com.comuctiva.comuctiva.Dto.VehiculoDto;
import com.comuctiva.comuctiva.Dto.VehiculoEstadoDto;
import com.comuctiva.comuctiva.Dto.VehiculoUpdateDto;
import com.comuctiva.comuctiva.services.VehiculoServices;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    
    private final VehiculoServices vehiculoServices;
    
    public VehiculoController(VehiculoServices vehiculoServices) {
        this.vehiculoServices = vehiculoServices;
    }
    
    @GetMapping
    public ResponseEntity<List<VehiculoDto>> listarTodos() {
        List<VehiculoDto> vehiculos = vehiculoServices.listarTodos();
        return ResponseEntity.ok(vehiculos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDto> obtenerPorId(@PathVariable Integer id) {
        try {
            VehiculoDto vehiculo = vehiculoServices.buscarPorId(id);
            return ResponseEntity.ok(vehiculo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody VehiculoCreateDto createDto) {
        try {
            VehiculoDto vehiculo = vehiculoServices.crear(createDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje", "Vehículo creado exitosamente", "vehiculo", vehiculo));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear vehículo", "detalles", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody VehiculoUpdateDto updateDto) {
        try {
            updateDto.setId_vehiculo(id);
            VehiculoDto vehiculo = vehiculoServices.actualizar(updateDto);
            return ResponseEntity.ok(Map.of("mensaje", "Vehículo actualizado exitosamente", "vehiculo", vehiculo));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al actualizar vehículo", "detalles", e.getMessage()));
        }
    }
    
    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id, @Valid @RequestBody VehiculoEstadoDto estadoDto) {
        try {
            VehiculoDto vehiculo = vehiculoServices.cambiarEstado(id, estadoDto);
            return ResponseEntity.ok(Map.of("mensaje", "Estado actualizado exitosamente", "vehiculo", vehiculo));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al cambiar estado", "detalles", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            vehiculoServices.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "Vehículo eliminado exitosamente"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al eliminar vehículo", "detalles", e.getMessage()));
        }
    }
}
