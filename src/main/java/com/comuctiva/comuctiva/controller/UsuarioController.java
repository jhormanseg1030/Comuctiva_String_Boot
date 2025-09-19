package com.comuctiva.comuctiva.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.UsuarioCreateDto;
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
    public ResponseEntity<UsuarioDto> crearUsuario( @Valid @RequestBody UsuarioCreateDto usuarioCreateDto) {
        UsuarioDto nuevoUsuario = usuarioServices.crearUsuario(usuarioCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
    
}
