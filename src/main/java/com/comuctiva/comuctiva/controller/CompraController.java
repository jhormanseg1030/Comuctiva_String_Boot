package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.services.CompraServices;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/compras")
public class CompraController {
        
    private final CompraServices compraServices;

    public CompraController(CompraServices compraServices) {
        this.compraServices = compraServices;
    }

    @PostMapping("/realizarcompra")
    public ResponseEntity<CompraDto> crearCompra (@Valid @RequestBody CompraDto compraDto){
        CompraDto nuevaCompra= compraServices.crearCompra(compraDto);
        return ResponseEntity.ok(nuevaCompra);
    }
    }

