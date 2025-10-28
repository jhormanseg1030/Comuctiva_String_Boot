package com.comuctiva.comuctiva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador de utilidad para pruebas de encriptación
 * IMPORTANTE: Este controlador debe ser eliminado o deshabilitado en producción
 */
@RestController
@RequestMapping("/api/test")
public class PasswordTestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Endpoint para encriptar una contraseña (solo para pruebas)
     * POST /api/test/encrypt
     * Body: { "password": "miPassword123" }
     */
    @PostMapping("/encrypt")
    public ResponseEntity<Map<String, String>> encryptPassword(@RequestBody Map<String, String> request) {
        String plainPassword = request.get("password");
        
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "La contraseña no puede estar vacía");
            return ResponseEntity.badRequest().body(error);
        }

        String encryptedPassword = passwordEncoder.encode(plainPassword);
        
        Map<String, String> response = new HashMap<>();
        response.put("plainPassword", plainPassword);
        response.put("encryptedPassword", encryptedPassword);
        response.put("length", String.valueOf(encryptedPassword.length()));
        
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para verificar si una contraseña coincide con un hash
     * POST /api/test/verify
     * Body: { "password": "miPassword123", "hash": "$2a$10$..." }
     */
    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyPassword(@RequestBody Map<String, String> request) {
        String plainPassword = request.get("password");
        String hash = request.get("hash");
        
        if (plainPassword == null || hash == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Se requieren 'password' y 'hash'");
            return ResponseEntity.badRequest().body(error);
        }

        boolean matches = passwordEncoder.matches(plainPassword, hash);
        
        Map<String, Object> response = new HashMap<>();
        response.put("password", plainPassword);
        response.put("hash", hash);
        response.put("matches", matches);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint de información sobre BCrypt
     * GET /api/test/info
     */
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("message", "BCrypt Password Encoder está activo");
        info.put("algorithm", "BCrypt");
        info.put("strength", "10 (default)");
        info.put("note", "Este endpoint debe ser removido en producción");
        
        Map<String, String> examples = new HashMap<>();
        examples.put("admin123", passwordEncoder.encode("admin123"));
        examples.put("1234", passwordEncoder.encode("1234"));
        
        info.put("examples", examples);
        
        return ResponseEntity.ok(info);
    }
}
