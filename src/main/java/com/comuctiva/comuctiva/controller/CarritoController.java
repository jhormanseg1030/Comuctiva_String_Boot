package com.comuctiva.comuctiva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.services.CarritoServices;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/carrito")
public class CarritoController {
    private final CarritoServices carritoServices;

    public CarritoController(CarritoServices carritoServices) {
        this.carritoServices = carritoServices;
    }

    @PostMapping("/cre")
    public ResponseEntity<CarritoDto> crearCarrito( @Valid @RequestBody CarritoDto carritoDto){
        CarritoDto nuevoCarrito = carritoServices.crearCarrito(carritoDto);
        return ResponseEntity.ok(nuevoCarrito);
    }
}
