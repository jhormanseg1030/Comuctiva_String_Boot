package com.comuctiva.comuctiva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.CarritoCreateDto;
import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.Dto.CarritoUpdateDto;
import com.comuctiva.comuctiva.services.CarritoServices;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/carrito")
public class CarritoController {
    private final CarritoServices carritoServices;

    public CarritoController(CarritoServices carritoServices) {
        this.carritoServices = carritoServices;
    }

    @PostMapping
    public ResponseEntity<?> crearCarrito(@Valid @RequestBody CarritoCreateDto carritoCreateDto) {
        try{
            CarritoDto carrito = carritoServices.crearCarrito(carritoCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of("mensaje", "Carrito creado con exito", "Detalles", carrito));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("mensaje",ex.getMessage()));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error","error al crear un carrito", "detalles", ex.getMessage()));
        }
    }

    @GetMapping("/{id_carrit}")
    public ResponseEntity<CarritoDto> obtenerPorId(@PathVariable Integer id_carrit){
        CarritoDto carrito = carritoServices.carritoPorId(id_carrit);
        return ResponseEntity.ok(carrito);
    }

    @GetMapping
    public ResponseEntity<List<CarritoDto>> listar(){
        List<CarritoDto> carrito = carritoServices.listartodos();
        return ResponseEntity.ok(carrito);
    }

    @PutMapping("/{id_carrito}")
    public ResponseEntity<CarritoDto> putActu(@PathVariable Integer id_carrito, @RequestBody CarritoUpdateDto carritoUpdateDto) {
        carritoUpdateDto.setId_carrit(id_carrito);
        CarritoDto actualizado = carritoServices.actualizarCarrito(carritoUpdateDto);
        return ResponseEntity.ok(actualizado);
    }
}
