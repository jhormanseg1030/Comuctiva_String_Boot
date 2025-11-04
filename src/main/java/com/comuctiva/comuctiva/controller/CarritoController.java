package com.comuctiva.comuctiva.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.ActualizarCantidadDto;
import com.comuctiva.comuctiva.Dto.AgregarCarritoDto;
import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import com.comuctiva.comuctiva.services.CarritoServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/carrito")
@CrossOrigin(origins = "http://localhost:5173")
public class CarritoController {

    @Autowired
    private CarritoServices carritoServices;
    
    @Autowired
    private UsuarioRepositories usuarioRepo;

    /**
     * GET /api/carrito
     * Ver mi carrito (se crea automáticamente si no existe)
     */
    @GetMapping
    public ResponseEntity<?> obtenerCarrito(Authentication auth) {
        try {
            Integer idUsuario = obtenerIdUsuario(auth);
            CarritoDto carrito = carritoServices.obtenerCarrito(idUsuario);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("Error", e.getMessage()));
        }
    }

    /**
     * POST /api/carrito
     * Agregar producto al carrito
     * Body: { "idProducto": 15, "cantidad": 2 }
     */
    @PostMapping
    public ResponseEntity<?> agregarProducto(
        @Valid @RequestBody AgregarCarritoDto dto, 
        Authentication auth
    ) {
        try {
            Integer idUsuario = obtenerIdUsuario(auth);
            CarritoDto carrito = carritoServices.agregarProducto(idUsuario, dto);
            return ResponseEntity.ok(Map.of(
                "Mensaje", "Producto agregado al carrito",
                "Carrito", carrito
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("Error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("Error", "Error al agregar producto al carrito", "Detalles", e.getMessage()));
        }
    }

    /**
     * PUT /api/carrito/{idProducto}
     * Actualizar cantidad de un producto
     * Body: { "cantidad": 5 }
     */
    @PutMapping("/{idProducto}")
    public ResponseEntity<?> actualizarCantidad(
        @PathVariable Integer idProducto,
        @Valid @RequestBody ActualizarCantidadDto dto,
        Authentication auth
    ) {
        try {
            Integer idUsuario = obtenerIdUsuario(auth);
            CarritoDto carrito = carritoServices.actualizarCantidad(idUsuario, idProducto, dto.getCantidad());
            return ResponseEntity.ok(Map.of(
                "Mensaje", "Cantidad actualizada",
                "Carrito", carrito
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("Error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("Error", "Error al actualizar cantidad", "Detalles", e.getMessage()));
        }
    }

    /**
     * DELETE /api/carrito/{idProducto}
     * Eliminar producto del carrito
     */
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<?> eliminarProducto(
        @PathVariable Integer idProducto,
        Authentication auth
    ) {
        try {
            Integer idUsuario = obtenerIdUsuario(auth);
            CarritoDto carrito = carritoServices.eliminarProducto(idUsuario, idProducto);
            return ResponseEntity.ok(Map.of(
                "Mensaje", "Producto eliminado del carrito",
                "Carrito", carrito
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("Error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("Error", "Error al eliminar producto", "Detalles", e.getMessage()));
        }
    }

    /**
     * DELETE /api/carrito
     * Vaciar carrito completo
     */
    @DeleteMapping
    public ResponseEntity<?> vaciarCarrito(Authentication auth) {
        try {
            Integer idUsuario = obtenerIdUsuario(auth);
            carritoServices.vaciarCarrito(idUsuario);
            return ResponseEntity.ok(Map.of("Mensaje", "Carrito vaciado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("Error", e.getMessage()));
        }
    }

    /**
     * Método auxiliar para obtener el ID del usuario autenticado
     */
    private Integer obtenerIdUsuario(Authentication auth) {
        String username = auth.getName();
        Usuario usuario = usuarioRepo.findByNumDoc(Long.parseLong(username));
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no autenticado");
        }
        return usuario.getId_Usuario();
    }
}
