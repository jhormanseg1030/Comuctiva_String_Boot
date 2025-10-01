package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Pedi_ProducDto;
import com.comuctiva.comuctiva.services.Pedi_ProducServices;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/pedi_produc")
public class Pedi_ProducController {
    
    private final Pedi_ProducServices pedi_producServices;
    
    public Pedi_ProducController(Pedi_ProducServices pedi_producServices) {
        this.pedi_producServices = pedi_producServices;
    }

    @PostMapping
    public ResponseEntity<Pedi_ProducDto> asignar(@Valid @RequestBody Pedi_ProducDto entity) {
        Pedi_ProducDto asig = pedi_producServices.asignar(entity);
        return ResponseEntity.ok(asig);
    }
    
}
