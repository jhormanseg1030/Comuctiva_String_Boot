package com.comuctiva.comuctiva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.BarrioDto;
import com.comuctiva.comuctiva.services.BarrioServices;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/barrio")
public class BarrioController {

    private final BarrioServices barrioServices;

    public BarrioController(BarrioServices barrioServices) {
        this.barrioServices = barrioServices;
    }

    @PostMapping("/crear")
    public ResponseEntity<BarrioDto> crearBarrio( @Valid @RequestBody BarrioDto barrioDto) {
        BarrioDto entity = barrioServices.crearBarrioDto(barrioDto);
        return ResponseEntity.ok(entity);
    }
    
}
