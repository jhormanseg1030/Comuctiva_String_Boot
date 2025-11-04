package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Produc_CarriDto;
import com.comuctiva.comuctiva.services.Produc_CarriServices;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    // ✅ Listar productos de un carrito
    @GetMapping("/carrito/{carritoId}")
    public ResponseEntity<List<Produc_CarriDto>> listarPorCarrito(@PathVariable Integer carritoId){
        List<Produc_CarriDto> productos = produc_CarriServices.listarCarri(carritoId);
        return ResponseEntity.ok(productos);
    }
    
    // ✅ Listar carritos que contienen un producto
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Produc_CarriDto>> listarPorProducto(@PathVariable Integer productoId){
        List<Produc_CarriDto> carritos = produc_CarriServices.listarProduc(productoId);
        return ResponseEntity.ok(carritos);
    }
    
    // ✅ Eliminar producto de un carrito
    @DeleteMapping("/carrito/{carritoId}/producto/{productoId}")
    public ResponseEntity<String> eliminar(
        @PathVariable Integer carritoId,
        @PathVariable Integer productoId
    ){
        produc_CarriServices.eliminar(productoId, carritoId);
        return ResponseEntity.ok("Producto eliminado del carrito");
    }
}
