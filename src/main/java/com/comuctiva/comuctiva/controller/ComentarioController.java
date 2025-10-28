package com.comuctiva.comuctiva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.ComentarioCreateDto;
import com.comuctiva.comuctiva.Dto.ComentarioDto;
import com.comuctiva.comuctiva.Dto.ComentarioUpdateDto;
import com.comuctiva.comuctiva.services.ComentarioServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/comentarios")
public class ComentarioController {
    
    private final ComentarioServices comentarioServices;

    public ComentarioController(ComentarioServices comentarioServices) {
        this.comentarioServices = comentarioServices;
    }

    // Crear un nuevo comentario
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ComentarioCreateDto comentarioCreate) {
        try {
            ComentarioDto comentario = comentarioServices.crearComentario(comentarioCreate);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("Mensaje", "Comentario creado exitosamente", "Detalles", comentario));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("Mensaje", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("Error", "Error al crear el comentario", "Detalles", ex.getMessage()));
        }
    }

    // Obtener un comentario por ID
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDto> obtenerId(@PathVariable Integer id) {
        ComentarioDto comentario = comentarioServices.buscarPorId(id);
        return ResponseEntity.ok(comentario);
    }

    // Listar todos los comentarios
    @GetMapping
    public ResponseEntity<List<ComentarioDto>> listar() {
        List<ComentarioDto> comentarios = comentarioServices.listarTodos();
        return ResponseEntity.ok(comentarios);
    }

    // Listar comentarios por producto
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<ComentarioDto>> listarPorProducto(@PathVariable Integer idProducto) {
        List<ComentarioDto> comentarios = comentarioServices.listarPorProducto(idProducto);
        return ResponseEntity.ok(comentarios);
    }

    // Listar comentarios por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ComentarioDto>> listarPorUsuario(@PathVariable Integer idUsuario) {
        List<ComentarioDto> comentarios = comentarioServices.listarPorUsuario(idUsuario);
        return ResponseEntity.ok(comentarios);
    }

    // Actualizar un comentario
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDto> actualizar(@PathVariable Integer id, 
                                                     @RequestBody ComentarioUpdateDto updateDto) {
        updateDto.setIdComentario(id);
        ComentarioDto actualizado = comentarioServices.actualizarComentario(updateDto);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un comentario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        comentarioServices.eliminarComentario(id);
        return ResponseEntity.ok().build();
    }
}
