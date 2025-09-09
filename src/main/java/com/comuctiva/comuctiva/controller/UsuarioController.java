package com.comuctiva.comuctiva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.UsuarioDto;
import com.comuctiva.comuctiva.services.UsuarioServices;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private final UsuarioServices usuarioServices;
    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }
    
    @PostMapping("/crear")
    public ResponseEntity<UsuarioDto> crearUsuario( @Valid @RequestBody UsuarioDto usuarioDto) {
        UsuarioDto nuevoUsuario = usuarioServices.crearUsuario(usuarioDto);
        return ResponseEntity.ok(nuevoUsuario);
    }
    
}
