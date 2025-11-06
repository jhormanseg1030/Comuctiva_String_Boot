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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }

        // ✅ Asegurate que estado tenga un valor
        if (productoCreateDto.getEstado() == null || productoCreateDto.getEstado().isBlank()) {
            productoCreateDto.setEstado("pendiente");
        }

        productoCreateDto.setId_usuario(vendedor.getId_Usuario());
        
        ProductoDto productoCreado = productoServices.crearProducto(productoCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
        
    } catch (IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
    } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear producto: " + ex.getMessage());
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


    @GetMapping("/mis-productos")
    public ResponseEntity<List<ProductoDto>> misProdutos(Authentication auth) {
        try {
            String username = auth.getName();
            Usuario usuario = usuarioRepositories.findByNumDoc(Long.parseLong(username));
            
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            
            List<ProductoDto> misProductos = productoServices.obtenerProductosPorVendedor(usuario.getId_Usuario());
            return ResponseEntity.ok(misProductos);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

        @GetMapping("/mis-productos/{id_producto}")
    public ResponseEntity<ProductoDto> miProductoPorId(@PathVariable Integer id_producto, Authentication auth) {
        try {
            String username = auth.getName();
            Usuario usuario = usuarioRepositories.findByNumDoc(Long.parseLong(username));
            
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            
            ProductoDto producto = productoServices.obtenerProductoPorIdYVendedor(id_producto, usuario.getId_Usuario());
            
            if (producto == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(null); // No es tu producto
            }
            
            return ResponseEntity.ok(producto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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


    @PutMapping("/mis-productos/{id_producto}")
    public ResponseEntity<?> actualizarMiProducto(
            @PathVariable Integer id_producto,
            @Valid @RequestBody ProductoUpdateDto productoUpdateDto,
            Authentication auth) {
        try {
            String username = auth.getName();
            Usuario usuario = usuarioRepositories.findByNumDoc(Long.parseLong(username));
            
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            
            ProductoDto productoActualizado = productoServices.actualizarProductoVendedor(
                id_producto, 
                usuario.getId_Usuario(), 
                productoUpdateDto
            );
            
            if (productoActualizado == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No puedes editar este producto");
            }
            
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
        }
    }

    @DeleteMapping("/mis-productos/{id_producto}")
    public ResponseEntity<?> desactivarMiProducto(@PathVariable Integer id_producto, Authentication auth) {
        try {
            String username = auth.getName();
            Usuario usuario = usuarioRepositories.findByNumDoc(Long.parseLong(username));
            
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            
            boolean desactivado = productoServices.desactivarProductoVendedor(id_producto, usuario.getId_Usuario());
            
            if (!desactivado) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No puedes desactivar este producto");
            }
            
            return ResponseEntity.ok("Producto desactivado correctamente");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
        }
    }

// ✅ OPCIONAL: Restaurar producto eliminado
    @PutMapping("/mis-productos/{id_producto}/restaurar")
    public ResponseEntity<?> activarMiProducto(@PathVariable Integer id_producto, Authentication auth) {
        try {
            String username = auth.getName();
            Usuario usuario = usuarioRepositories.findByNumDoc(Long.parseLong(username));
            
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            
            boolean activado = productoServices.activarProductoVendedor(id_producto, usuario.getId_Usuario());
            
            if (!activado) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No puedes activar este producto");
            }
            
            return ResponseEntity.ok("Producto activado correctamente");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
        }
    }


// 🆕 Activar producto
@PutMapping("/{id_producto}/activar")
public ResponseEntity<?> activarProducto(@PathVariable Integer id_producto) {
    try {
        productoServices.restaurarProducto(id_producto);
        return ResponseEntity.ok(Map.of("Mensaje", "Producto activado exitosamente", "activo", true));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("Error", "Error al activar producto: " + e.getMessage()));
    }
}

// 🆕 Desactivar producto
@PutMapping("/{id_producto}/desactivar")
public ResponseEntity<?> desactivarProducto(@PathVariable Integer id_producto) {
    try {
        productoServices.desactivarProducto(id_producto);
        return ResponseEntity.ok(Map.of("Mensaje", "Producto desactivado exitosamente", "activo", false));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("Error", "Error al desactivar producto: " + e.getMessage()));
    }
}

// 🆕 Toggle estado activo/inactivo del producto (más fácil de usar)
@PutMapping("/{id_producto}/toggle-estado")
public ResponseEntity<?> toggleEstadoProducto(@PathVariable Integer id_producto) {
    try {
        // Obtener el producto actual
        ProductoDto producto = productoServices.productoPorId(id_producto);
        
        if (producto.getActivo() != null && producto.getActivo()) {
            // Si está activo, desactivar
            productoServices.desactivarProducto(id_producto);
            return ResponseEntity.ok(Map.of(
                "Mensaje", "Producto desactivado exitosamente", 
                "activo", false,
                "producto", producto.getNombre_Producto()
            ));
        } else {
            // Si está inactivo, activar
            productoServices.restaurarProducto(id_producto);
            return ResponseEntity.ok(Map.of(
                "Mensaje", "Producto activado exitosamente", 
                "activo", true,
                "producto", producto.getNombre_Producto()
            ));
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("Error", "Error al cambiar estado del producto: " + e.getMessage()));
    }
}

    @DeleteMapping("/{id_producto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id_producto) {
        productoServices.eliminarProducto(id_producto);
        return ResponseEntity.ok().build();
    }
}