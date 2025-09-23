package com.comuctiva.comuctiva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.ProductoCreateDto;
import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.Dto.ProductoUpdateDto;
import com.comuctiva.comuctiva.services.ProductoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/producto")
public class ProductoController {

    private final ProductoServices productoServices;

    public ProductoController(ProductoServices productoServices) {
        this.productoServices = productoServices;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductoCreateDto productoCreateDto) {
        try{
            ProductoDto producto = productoServices.crearProducto(productoCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of("mensaje", "Producto creado con éxito", "detalles", producto));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("mensaje",ex.getMessage()));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("mensaje", "Error al crear un producto", "detalles", ex.getMessage()));
        }
    }
    
    @GetMapping("/{id_producto}")
    public ResponseEntity<ProductoDto> obtenerId(@PathVariable Integer id_producto) {
        ProductoDto producto = productoServices.productoPorId(id_producto);
        return ResponseEntity.ok(producto);
    }
    
    @GetMapping
    public ResponseEntity<List<ProductoDto>> listar(){
        List<ProductoDto> productos = productoServices.listar();
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{id_producto}")
    public ResponseEntity<ProductoDto> putActualizar(@PathVariable Integer id_producto, @RequestBody ProductoUpdateDto productoUpdate) {
        productoUpdate.setId_producto(id_producto);
        ProductoDto actualizado = productoServices.actualizarProducto(productoUpdate);
        return ResponseEntity.ok(actualizado);
    }
}
