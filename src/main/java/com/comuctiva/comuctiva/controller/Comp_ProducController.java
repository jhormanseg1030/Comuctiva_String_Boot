package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.services.Comp_ProducServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/compra-producto")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Comp_ProducController {

    @Autowired
    private Comp_ProducServices comp_ProducServices;

    // 🆕 OBTENER PRODUCTOS DE UNA COMPRA
    @GetMapping("/compra/{id_compra}")
    public ResponseEntity<?> obtenerProductosPorCompra(@PathVariable Integer id_compra) {
        try {
            List<Com_ProducDto> productos = comp_ProducServices.obtenerProductosPorCompra(id_compra);
            return ResponseEntity.ok(productos);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
        }
    }

    // 🆕 OBTENER PRODUCTO ESPECÍFICO DE COMPRA
    @GetMapping("/{id_com_produc}")
    public ResponseEntity<?> obtenerProductoCompra(@PathVariable Integer id_com_produc) {
        try {
            Com_ProducDto producto = comp_ProducServices.obtenerProductoCompra(id_com_produc);
            
            if (producto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto de compra no encontrado");
            }

            return ResponseEntity.ok(producto);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
        }
    }
}
