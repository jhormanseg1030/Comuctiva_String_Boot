package com.comuctiva.comuctiva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import com.comuctiva.comuctiva.services.ProductoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/producto")
public class ProductoController {

    private final ProductoServices productoServices;
    private final UsuarioRepositories usuarioRepositories;

    public ProductoController(ProductoServices productoServices, UsuarioRepositories usuarioRepositories) {
        this.productoServices = productoServices;
        this.usuarioRepositories = usuarioRepositories;
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ProductoCreateDto productoCreateDto, Authentication auth) {
        try {
            String username = auth.getName(); // NumDoc del JWT
            Usuario vendedor = usuarioRepositories.findByNumDoc(Long.parseLong(username));

            if (vendedor == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            productoCreateDto.setIdUsuario(vendedor.getId_Usuario());
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
        // Ya no se filtra por vendedor, se listan todos los productos
        List<ProductoDto> productos = productoServices.listar();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/mis-productos")
    public ResponseEntity<List<ProductoDto>> listarMisProductos(Authentication auth) {
        try {
            String username = auth.getName(); // NumDoc del JWT
            Usuario vendedor = usuarioRepositories.findByNumDoc(Long.parseLong(username));
            
            if (vendedor == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            
            List<ProductoDto> misProductos = productoServices.listarMisProductos(vendedor.getId_Usuario());
            return ResponseEntity.ok(misProductos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/mis-productos/{id_producto}")
public ResponseEntity<?> obtenerMiProductoPorId(@PathVariable Integer id_producto, Authentication auth) {
    try {
        String username = auth.getName();
        Usuario vendedor = usuarioRepositories.findByNumDoc(Long.parseLong(username));
        
        if (vendedor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        // Obtener el producto
        ProductoDto producto = productoServices.productoPorId(id_producto);
        
        // ✅ Verificar que el producto sea del usuario autenticado
        if (!producto.getId_usuario().equals(vendedor.getId_Usuario())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("Error", "No tienes permiso para ver este producto"));
        }
        
        return ResponseEntity.ok(producto);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("Error", "Producto no encontrado"));
    }
}

    @PutMapping("/{id_producto}")
    public ResponseEntity<ProductoDto> putActualizar(@PathVariable Integer id_producto, @RequestBody ProductoUpdateDto productoUpdate) {
        productoUpdate.setId_producto(id_producto);
        ProductoDto actualizado = productoServices.actualizarProducto(productoUpdate);
        return ResponseEntity.ok(actualizado);
    }


    @PutMapping("/mis-productos/{id_producto}")
        public ResponseEntity<?> actualizarMiProducto(
            @PathVariable Integer id_producto,
            @Valid @RequestBody ProductoUpdateDto productoUpdate,
            Authentication auth
        ){
                    try {
                String username = auth.getName();
                Usuario vendedor = usuarioRepositories.findByNumDoc(Long.parseLong(username));
                
                if (vendedor == null) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("Error", "Usuario no autenticado"));
                }
                
                // ✅ Verificar que el producto exista
                ProductoDto productoExistente = productoServices.productoPorId(id_producto);
                
                // ✅ Verificar que el producto sea del usuario autenticado
                if (!productoExistente.getId_usuario().equals(vendedor.getId_Usuario())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of("Error", "No tienes permiso para editar este producto"));
                }
                
                // ✅ Actualizar el producto
                productoUpdate.setId_producto(id_producto);
                ProductoDto actualizado = productoServices.actualizarProducto(productoUpdate);
                
                return ResponseEntity.ok(Map.of(
                    "Mensaje", "Producto actualizado exitosamente",
                    "Detalles", actualizado
                ));
                
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("Error", "Producto no encontrado"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("Error", "Error al actualizar producto", "Detalles", e.getMessage()));
    }
}

    @DeleteMapping("/mis-productos/{id_producto}")
    public ResponseEntity<?> desactivarMiProducto(@PathVariable Integer id_producto, Authentication auth) {
    try {
        String username = auth.getName();
        Usuario vendedor = usuarioRepositories.findByNumDoc(Long.parseLong(username));
        
        if (vendedor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("Error", "Usuario no autenticado"));
        }
        
        // Verificar que el producto sea del usuario
        ProductoDto producto = productoServices.productoPorId(id_producto);
        if (!producto.getId_usuario().equals(vendedor.getId_Usuario())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("Error", "No tienes permiso para eliminar este producto"));
        }
        
        // Desactivar (soft delete)
        productoServices.desactivarProducto(id_producto);
        
        return ResponseEntity.ok(Map.of("Mensaje", "Producto desactivado exitosamente"));
        
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("Error", "Error al desactivar producto", "Detalles", e.getMessage()));
    }
}

// ✅ OPCIONAL: Restaurar producto eliminado
@PutMapping("/mis-productos/{id_producto}/restaurar")
public ResponseEntity<?> restaurarMiProducto(@PathVariable Integer id_producto, Authentication auth) {
    try {
        String username = auth.getName();
        Usuario vendedor = usuarioRepositories.findByNumDoc(Long.parseLong(username));
        
        if (vendedor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("Error", "Usuario no autenticado"));
        }
        
        productoServices.restaurarProducto(id_producto);
        
        return ResponseEntity.ok(Map.of("Mensaje", "Producto restaurado exitosamente"));
        
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("Error", "Error al restaurar producto"));
    }
}

    @DeleteMapping("/{id_producto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id_producto) {
        productoServices.eliminarProducto(id_producto);
        return ResponseEntity.ok().build();
    }
    
}
