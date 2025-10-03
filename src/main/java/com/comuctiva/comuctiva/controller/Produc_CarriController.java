package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Produc_CarriDto;
import com.comuctiva.comuctiva.services.Produc_CarriServices;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/produc_carri")
public class Produc_CarriController {
    private final Produc_CarriServices produc_CarriServices;

    public Produc_CarriController(Produc_CarriServices produc_CarriServices){
        this.produc_CarriServices = produc_CarriServices;
    }

    @PostMapping
    public ResponseEntity<Produc_CarriDto> asignar(@Valid @RequestBody Produc_CarriDto entity){
        Produc_CarriDto asi = produc_CarriServices.asignar(entity);
        return ResponseEntity.ok(asi);
    }
}
