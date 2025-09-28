package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.services.Comp_ProducServices;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/ConsultaCompra")
public class Comp_ProducController {
    private final Comp_ProducServices services;

    public Comp_ProducController(Comp_ProducServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<Com_ProducDto> asignar(@Valid @RequestBody Com_ProducDto entity) {
        Com_ProducDto asignado = services.asignar(entity);
        return ResponseEntity.ok(asignado);
    }
    
}
