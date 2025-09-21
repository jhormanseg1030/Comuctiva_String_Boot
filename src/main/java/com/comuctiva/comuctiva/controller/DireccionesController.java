package com.comuctiva.comuctiva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.DireccionesCreateDto;
import com.comuctiva.comuctiva.Dto.DireccionesDto;
import com.comuctiva.comuctiva.Dto.DireccionesUpdateDto;
import com.comuctiva.comuctiva.services.DireccionesServices;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/direcciones")
public class DireccionesController {

    private final DireccionesServices direccionesServices;
    
    public DireccionesController(DireccionesServices direccionesServices) {
        this.direccionesServices = direccionesServices;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearDireccion( @Valid @RequestBody DireccionesCreateDto direccionesCreateDto) {
        try{
        DireccionesDto nuevaDireccion = direccionesServices.crearDireccion(direccionesCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(Map.of("Mensaje", "Dirección creada con éxito", "Detalles", nuevaDireccion));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("Mensaje", "Ya existe una dirección con ese número"));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("Mensaje", "Error al crear la dirección"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionesDto> obtenerId(@PathVariable Integer id_direc) {
        DireccionesDto direccion = direccionesServices.direccionPorId(id_direc);
        return ResponseEntity.ok(direccion);
    }
    
    @GetMapping
    public ResponseEntity<List<DireccionesDto>> listar() {
        List<DireccionesDto> direcciones = direccionesServices.listarTodas();
        return ResponseEntity.ok(direcciones);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DireccionesDto> putActualizar(@PathVariable Integer id_direc, @RequestBody DireccionesUpdateDto direccUpdate) {
        direccUpdate.setId_direc(id_direc);
        DireccionesDto actualizado = direccionesServices.actualizarDireccion(direccUpdate);
        return ResponseEntity.ok(actualizado);
    }
}
