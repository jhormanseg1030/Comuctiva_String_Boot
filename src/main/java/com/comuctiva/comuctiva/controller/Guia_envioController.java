package com.comuctiva.comuctiva.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Guia_EnvioCrearDtos;
import com.comuctiva.comuctiva.Dto.Guia_EnvioDto;
import com.comuctiva.comuctiva.services.Guia_EnvioServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/guia_envio")
public class Guia_envioController {
    
    private final Guia_EnvioServices guia_EnvioServices;
    public Guia_envioController(Guia_EnvioServices guia_EnvioServices) {
        this.guia_EnvioServices = guia_EnvioServices;
    }

    @PostMapping("/crearGuia")
    public ResponseEntity<Guia_EnvioDto> crearGuia_Envio( @Valid @RequestBody Guia_EnvioCrearDtos guia_EnvioCrearDtos) {
        Guia_EnvioDto nuevaGuia_Envio = guia_EnvioServices.crearGuia_Envio(guia_EnvioCrearDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaGuia_Envio);
    }
}
