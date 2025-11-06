package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.VentaDto;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import com.comuctiva.comuctiva.services.VentasServices;

@RestController
@RequestMapping("/api/venta")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VentaController {

    @Autowired
    private VentasServices ventaServices;

    @Autowired
    private UsuarioRepositories usuarioRepositories;

    // 🆕 OBTENER MIS VENTAS (como vendedor)
    @GetMapping("/mis-ventas")
    public ResponseEntity<?> obtenerMisVentas(Authentication auth) {
        try {
            String username = auth.getName();
            Usuario usuario = usuarioRepositories.findByNumDoc(Long.parseLong(username));

            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            List<VentaDto> misVentas = ventaServices.obtenerMisVentas(usuario.getId_Usuario());
            return ResponseEntity.ok(misVentas);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
        }
    }

    // 🆕 LISTAR TODAS LAS VENTAS (ADMIN)
    @GetMapping
    public ResponseEntity<?> listarTodas() {
        try {
            List<VentaDto> ventas = ventaServices.listarTodas();
            return ResponseEntity.ok(ventas);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
        }
    }
}
