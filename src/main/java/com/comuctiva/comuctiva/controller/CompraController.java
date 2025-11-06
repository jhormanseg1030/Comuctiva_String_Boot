package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.CompraCreateDto;
import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import com.comuctiva.comuctiva.services.CompraServices;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/compras")
public class CompraController {
        
    private final CompraServices compraServices;
    private final UsuarioRepositories usuarioRepositories;

    public CompraController(CompraServices compraServices, UsuarioRepositories usuarioRepositories ) {
        this.compraServices = compraServices;
        this.usuarioRepositories = usuarioRepositories;
    }
    @PostMapping
    public ResponseEntity<?> crearCompra(
        @Valid @RequestBody CompraCreateDto compraCreateDto,
        Authentication auth) {
        try {
            String username = auth.getName();
            Usuario usuario = usuarioRepositories.findByNumDoc(Long.parseLong(username));

            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario no encontrado");
            }

            // ✅ PASAR EL ID DEL USUARIO
            CompraDto compraCreada = compraServices.crearCompra(compraCreateDto, usuario.getId_Usuario());
            return ResponseEntity.status(HttpStatus.CREATED).body(compraCreada);

        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear compra: " + ex.getMessage());
        }
    }

    @GetMapping("/{id_compra}")
    public ResponseEntity<?> obtenerCompraPorId(@PathVariable Integer id_compra) {
        try {
            CompraDto compra = compraServices.obtenerCompraPorId(id_compra);
            
            if (compra == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Compra no encontrada");
            }

            return ResponseEntity.ok(compra);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
        }
    }

    @GetMapping("/mis-compras")
    public ResponseEntity<?> obtenerMisCompras(Authentication auth) {
        try {
            String username = auth.getName();
            Usuario usuario = usuarioRepositories.findByNumDoc(Long.parseLong(username));

            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // ✅ PASAR EL ID DEL USUARIO
            List<CompraDto> misCompras = compraServices.obtenerMisCompras(usuario.getId_Usuario());
            return ResponseEntity.ok(misCompras);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarCompras() {
        try {
            List<CompraDto> compras = compraServices.listarCompras();
            return ResponseEntity.ok(compras);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
        }
    }
}

