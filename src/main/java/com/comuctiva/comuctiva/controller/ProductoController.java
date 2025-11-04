package com.comuctiva.comuctiva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ProductoCreateDto productoCreateDto) {
        try {
            ProductoDto producto = productoServices.crearProducto(productoCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("Mensaje", "Producto creado exitosamente", "Detalles", producto));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("Error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("Error", "Error al crear producto", "Detalles", ex.getMessage()));
        }
    }
    
    @GetMapping("/{id_producto}")
    public ResponseEntity<ProductoDto> obtenerId(@PathVariable Integer id_producto) {
        ProductoDto producto = productoServices.productoPorId(id_producto);
        return ResponseEntity.ok(producto);
    }
    
    @GetMapping
    public ResponseEntity<List<ProductoDto>> listar() {
        List<ProductoDto> productos = productoServices.listar();
        return ResponseEntity.ok(productos);
    }

    // NUEVO: Listar productos pendientes de aprobación
    @GetMapping("/pendientes")
    public ResponseEntity<List<ProductoDto>> listarPendientes() {
        List<ProductoDto> productosPendientes = productoServices.listarPendientes();
        return ResponseEntity.ok(productosPendientes);
    }

    // NUEVO: Aprobar producto
    @PostMapping("/aprobar/{id}")
    public ResponseEntity<?> aprobarProducto(@PathVariable Integer id) {
        try {
            productoServices.cambiarEstadoProducto(id, "aprobado");
            return ResponseEntity.ok(Map.of("mensaje", "Producto aprobado correctamente"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    // NUEVO: Rechazar producto
    @PostMapping("/rechazar/{id}")
    public ResponseEntity<?> rechazarProducto(@PathVariable Integer id) {
        try {
            productoServices.cambiarEstadoProducto(id, "rechazado");
            return ResponseEntity.ok(Map.of("mensaje", "Producto rechazado correctamente"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @PutMapping("/{id_producto}")
    public ResponseEntity<ProductoDto> putActualizar(@PathVariable Integer id_producto, @RequestBody ProductoUpdateDto productoUpdate) {
        productoUpdate.setId_producto(id_producto);
        ProductoDto actualizado = productoServices.actualizarProducto(productoUpdate);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id_producto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id_producto) {
        productoServices.eliminarProducto(id_producto);
        return ResponseEntity.ok().build();
    }
}
