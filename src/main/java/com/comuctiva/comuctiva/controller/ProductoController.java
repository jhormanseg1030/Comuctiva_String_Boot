package com.comuctiva.comuctiva.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

        @GetMapping("/imagen/{nombreArchivo}")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombreArchivo) {
        try {
            Path ruta = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
            Resource resource = new FileSystemResource(ruta);
            
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }
            
            System.out.println("✅ SIRVIENDO IMAGEN: " + ruta.toString());
            
            return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
        } catch (Exception e) {
            System.out.println("❌ ERROR AL SERVIR IMAGEN: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

@PostMapping
public ResponseEntity<?> crear(
    @RequestParam("nombre_Producto") String nombreProducto,
    @RequestParam("valor") Double valor,
    @RequestParam("cantidad") Short cantidad,
    @RequestParam(value = "descripcion", required = false) String descripcion,
    @RequestParam("id_medida") Integer idMedida,
    @RequestParam(value = "categoria", required = false) String categoria,  
    @RequestParam(value = "imagen", required = false) MultipartFile imagen,
    Authentication auth
) {
    try {
        String username = auth.getName();
        Usuario vendedor = usuarioRepositories.findByNumDoc(Long.parseLong(username));

        if (vendedor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }

        // ✅ Guardar la imagen
        String rutaImagen = null;
        if (imagen != null && !imagen.isEmpty()) {
            try {
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
                Path ruta = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
                
                // Crear carpeta si no existe
                Files.createDirectories(ruta.getParent());
                Files.copy(imagen.getInputStream(), ruta);
                
                rutaImagen = nombreArchivo;
                System.out.println("✅ IMAGEN GUARDADA EN: " + ruta.toString());
                System.out.println("✅ NOMBRE DE IMAGEN: " + rutaImagen);
            } catch (Exception e) {
                System.out.println("❌ ERROR AL GUARDAR IMAGEN: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // ✅ Crear el producto
        ProductoCreateDto productoCreateDto = new ProductoCreateDto();
        productoCreateDto.setNombre_Producto(nombreProducto);
        productoCreateDto.setValor(valor);
        productoCreateDto.setCantidad(cantidad);
        productoCreateDto.setDescripcion(descripcion != null ? descripcion : "");
        productoCreateDto.setId_medida(idMedida);
        productoCreateDto.setCategoria(categoria != null ? categoria : "");
        productoCreateDto.setImagen(rutaImagen);
        productoCreateDto.setId_usuario(vendedor.getId_Usuario());

        ProductoDto productoCreado = productoServices.crearProducto(productoCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);

    } catch (IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
    } catch (Exception ex) {
        ex.printStackTrace();
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

@PutMapping("/{id_producto}/con-imagen")
public ResponseEntity<?> actualizarConImagen(
    @PathVariable Integer id_producto,
    @RequestParam(value = "nombre_Producto", required = false) String nombreProducto,
    @RequestParam(value = "valor", required = false) Double valor,
    @RequestParam(value = "cantidad", required = false) Short cantidad,
    @RequestParam(value = "descripcion", required = false) String descripcion,
    @RequestParam(value = "id_medida", required = false) Integer idMedida,
    @RequestParam(value = "categoria", required = false) String categoria,
    @RequestParam(value = "imagen", required = false) MultipartFile imagen,
    Authentication auth
) {
    try {
        String username = auth.getName();
        Usuario vendedor = usuarioRepositories.findByNumDoc(Long.parseLong(username));

        if (vendedor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }

        // ✅ Si hay nueva imagen, guardarla
        String rutaImagen = null;
        if (imagen != null && !imagen.isEmpty()) {
            try {
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
                Path ruta = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
                Files.createDirectories(ruta.getParent());
                Files.copy(imagen.getInputStream(), ruta);
                
                rutaImagen = nombreArchivo;
                System.out.println("✅ IMAGEN ACTUALIZADA EN: " + ruta.toString());
            } catch (Exception e) {
                System.out.println("❌ ERROR AL GUARDAR IMAGEN: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // ✅ Actualizar el producto
        ProductoUpdateDto productoUpdate = new ProductoUpdateDto();
        productoUpdate.setId_producto(id_producto);
        
        if (nombreProducto != null) productoUpdate.setNombre_Producto(nombreProducto);
        if (valor != null) productoUpdate.setValor(valor);
        if (cantidad != null) productoUpdate.setCantidad(cantidad);
        if (descripcion != null) productoUpdate.setDescripcion(descripcion);
        if (idMedida != null) productoUpdate.setId_medida(idMedida);
        if (categoria != null) productoUpdate.setCategoria(categoria);
        if (rutaImagen != null) productoUpdate.setImagen(rutaImagen);

        ProductoDto productoActualizado = productoServices.actualizarProducto(productoUpdate);
        return ResponseEntity.ok(productoActualizado);

    } catch (Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al actualizar producto: " + ex.getMessage());
    }
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