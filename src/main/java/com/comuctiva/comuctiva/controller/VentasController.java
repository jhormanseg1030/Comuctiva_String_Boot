package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.MiCompraDto;
import com.comuctiva.comuctiva.Dto.VentaDto;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import com.comuctiva.comuctiva.services.VentasServices;

@RestController
@RequestMapping("/api")
public class VentasController {
    
    private final VentasServices ventasServices;
    private final UsuarioRepositories usuarioRepositories;
    
    public VentasController(VentasServices ventasServices, UsuarioRepositories usuarioRepositories) {
        this.ventasServices = ventasServices;
        this.usuarioRepositories = usuarioRepositories;
    }
    
    /**
     * Obtiene las ventas del usuario autenticado
     * (productos que le compraron al usuario)
     * GET /api/mis-ventas
     */
    @GetMapping("/mis-ventas")
    public ResponseEntity<List<VentaDto>> obtenerMisVentas() {
        // Obtener el usuario autenticado desde SecurityContext
        org.springframework.security.core.Authentication authentication = 
            org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("Usuario no autenticado");
        }
        
        // El principal es el username (número de documento)
        String username = authentication.getPrincipal().toString();
        Long numDoc = Long.parseLong(username);
        
        Usuario usuario = usuarioRepositories.findByNumDoc(numDoc);
        
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        
        List<VentaDto> ventas = ventasServices.obtenerMisVentas(usuario.getId_Usuario());
        return ResponseEntity.ok(ventas);
    }
    
    /**
     * Obtiene las compras del usuario autenticado
     * (productos que el usuario compró)
     * GET /api/mis-compras
     */
    @GetMapping("/mis-compras")
    public ResponseEntity<List<MiCompraDto>> obtenerMisCompras() {
        System.out.println("=== ENDPOINT MIS-COMPRAS ===");
        
        // Obtener el usuario autenticado desde SecurityContext
        org.springframework.security.core.Authentication authentication = 
            org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        
        System.out.println("Authentication: " + authentication);
        System.out.println("Principal: " + (authentication != null ? authentication.getPrincipal() : "NULL"));
        
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("Usuario no autenticado");
        }
        
        // El principal es el username (número de documento)
        String username = authentication.getPrincipal().toString();
        System.out.println("Username from authentication: " + username);
        
        Long numDoc = Long.parseLong(username);
        System.out.println("NumDoc parseado: " + numDoc);
        
        Usuario usuario = usuarioRepositories.findByNumDoc(numDoc);
        System.out.println("Usuario encontrado: " + (usuario != null ? usuario.getNom_Usu() : "NULL"));
        
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        
        List<MiCompraDto> compras = ventasServices.obtenerMisCompras(usuario.getId_Usuario());
        System.out.println("Compras encontradas: " + compras.size());
        System.out.println("=== FIN MIS-COMPRAS ===");
        return ResponseEntity.ok(compras);
    }
}
