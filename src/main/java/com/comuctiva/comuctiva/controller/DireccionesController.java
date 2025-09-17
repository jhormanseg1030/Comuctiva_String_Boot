package com.comuctiva.comuctiva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.DireccionesDto;
import com.comuctiva.comuctiva.services.DireccionesServices;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/direcciones")
public class DireccionesController {

    private final DireccionesServices direccionesServices;
    
    public DireccionesController(DireccionesServices direccionesServices) {
        this.direccionesServices = direccionesServices;
    }

    @PostMapping("/crear")
    public ResponseEntity<DireccionesDto> crearDireccion( @Valid @RequestBody DireccionesDto direccionesDto) {
        DireccionesDto nuevaDireccion = direccionesServices.crearDireccion(direccionesDto);
        return ResponseEntity.ok(nuevaDireccion);
    }
    
}
