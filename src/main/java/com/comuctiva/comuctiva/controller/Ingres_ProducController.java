package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Ingres_ProducDto;
import com.comuctiva.comuctiva.services.Ingres_ProducServices;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ingresos_productos")
public class Ingres_ProducController {

    private final Ingres_ProducServices services;

    public Ingres_ProducController(Ingres_ProducServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<Ingres_ProducDto> asignar(@Valid @RequestBody Ingres_ProducDto entity) {
        Ingres_ProducDto guardar = services.asignar(entity);
        return ResponseEntity.ok(guardar);
    }
    
}
