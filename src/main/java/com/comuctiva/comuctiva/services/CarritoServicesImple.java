package com.comuctiva.comuctiva.services;

import com.comuctiva.comuctiva.Dto.AgregarCarritoDto;
import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.Mapper.CarritoMapper;
import com.comuctiva.comuctiva.models.Carrito;
import com.comuctiva.comuctiva.models.Produc_Carri;
import com.comuctiva.comuctiva.models.Produc_CarriId;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.CarritoRepositories;
import com.comuctiva.comuctiva.repositoryes.Produc_CarriRepositories;
import com.comuctiva.comuctiva.repositoryes.ProductoRepositorie;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
public class CarritoServicesImple implements CarritoServices {

    @Autowired
    private CarritoRepositories carritoRepo;
    
    @Autowired
    private Produc_CarriRepositories producCarriRepo;
    
    @Autowired
    private ProductoRepositorie productoRepo;

    @Autowired
    private UsuarioRepositories usuarioRepo;
    
    @Autowired
    private CarritoMapper carritoMapper;

    @Override
    public CarritoDto obtenerCarrito(Integer id_Usuario) {
        Carrito carrito = obtenerOCrearCarrito(id_Usuario);
        return carritoMapper.toCarritoDto(carrito);
    }

    @Override
    public CarritoDto agregarProducto(Integer id_Usuario, AgregarCarritoDto dto) {
        try {
            // 1. Obtener o crear carrito
            Carrito carrito = obtenerOCrearCarrito(id_Usuario);
            
            // 2. Validar que el producto exista y esté activo
            Producto producto = productoRepo.findById(dto.getIdProducto())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + dto.getIdProducto()));
            
            if (producto.getActivo() != null && !producto.getActivo()) {
                throw new IllegalArgumentException("El producto no está disponible");
            }
            
            // 3. Validar stock
            Short stock = producto.getCant() != null ? producto.getCant() : 0;
            if (stock < dto.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente. Disponible: " + stock);
            }
            
            // 4. Verificar si el producto ya está en el carrito
            Produc_Carri producCarri = producCarriRepo
                .findByCarritoAndProducto(carrito.getIdCarrito(), producto.getId_producto())
                .orElse(null);
            
            if (producCarri != null) {
                // Ya existe → Sumar cantidad
                int nuevaCantidad = producCarri.getCantidad() + dto.getCantidad();
                
                if (stock < nuevaCantidad) {
                    throw new IllegalArgumentException("Stock insuficiente. Disponible: " + stock + ", solicitado: " + nuevaCantidad);
                }
                
                producCarri.setCantidad(nuevaCantidad);
                producCarriRepo.save(producCarri);
                
            } else {
                // No existe → Crear nuevo
                producCarri = new Produc_Carri();
                
                Produc_CarriId id = new Produc_CarriId();
                id.setCarritoId(carrito.getIdCarrito());
                id.setProdId(producto.getId_producto());
                
                producCarri.setId(id);
                producCarri.setCarrito(carrito);
                producCarri.setProd(producto);
                producCarri.setCantidad(dto.getCantidad());
                producCarri.setNomprod(producto.getNomprod());
                producCarri.setFechaAgre(new Timestamp(System.currentTimeMillis()));
                
                producCarriRepo.save(producCarri);
            }
            
            // ✅ Recargar carrito completo desde la BD
            carrito = carritoRepo.findByUsuarioId_UsuarioWithItems(id_Usuario)
                .orElseThrow(() -> new IllegalArgumentException("Error al recargar carrito"));
            
            return carritoMapper.toCarritoDto(carrito);
            
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar producto al carrito: " + e.getMessage(), e);
        }
    }

    @Override
    public CarritoDto actualizarCantidad(Integer id_Usuario, Integer idProducto, Integer cantidad) {
        try {
            Carrito carrito = obtenerOCrearCarrito(id_Usuario);
            
            Produc_Carri producCarri = producCarriRepo
                .findByCarritoAndProducto(carrito.getIdCarrito(), idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado en el carrito"));
            
            Short stock = producCarri.getProd() != null && producCarri.getProd().getCant() != null
                ? producCarri.getProd().getCant()
                : 0;
                
            if (stock < cantidad) {
                throw new IllegalArgumentException("Stock insuficiente. Disponible: " + stock);
            }
            
            if (cantidad <= 0) {
                // ✅ Eliminar usando flush() para forzar cambios inmediatos
                producCarriRepo.delete(producCarri);
                producCarriRepo.flush();
            } else {
                producCarri.setCantidad(cantidad);
                producCarriRepo.save(producCarri);
            }
            
            // ✅ FORZAR recarga desde BD con clear()
            carritoRepo.flush();
            
            carrito = carritoRepo.findByUsuarioId_UsuarioWithItems(id_Usuario)
                .orElseThrow(() -> new IllegalArgumentException("Error al recargar carrito"));
            
            return carritoMapper.toCarritoDto(carrito);
            
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar cantidad: " + e.getMessage(), e);
        }
    }

    @Override
    public CarritoDto eliminarProducto(Integer id_Usuario, Integer idProducto) {
        try {
            Carrito carrito = obtenerOCrearCarrito(id_Usuario);
            
            // ✅ Usar deleteByCarritoAndProducto personalizado
            producCarriRepo.deleteByCarritoAndProducto(carrito.getIdCarrito(), idProducto);
            producCarriRepo.flush(); // ✅ Forzar ejecución inmediata
            
            // ✅ Recargar carrito desde BD
            carrito = carritoRepo.findByUsuarioId_UsuarioWithItems(id_Usuario)
                .orElseThrow(() -> new IllegalArgumentException("Error al recargar carrito"));
            
            return carritoMapper.toCarritoDto(carrito);
            
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public void vaciarCarrito(Integer id_Usuario) {
        try {
            Carrito carrito = carritoRepo.findByUsuarioId_UsuarioWithItems(id_Usuario)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));
            
            // ✅ MÉTODO 1: Eliminar por query directa
            if (carrito.getIdCarrito() != null) {
                producCarriRepo.deleteByCarritoId(carrito.getIdCarrito());
                producCarriRepo.flush(); // ✅ Forzar ejecución
            }
            
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al vaciar carrito: " + e.getMessage(), e);
        }
    }

    private Carrito obtenerOCrearCarrito(Integer id_Usuario) {
        return carritoRepo.findByUsuarioId_UsuarioWithItems(id_Usuario)
            .orElseGet(() -> {
                Usuario usuario = usuarioRepo.findById(id_Usuario)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id_Usuario));
                
                Carrito nuevoCarrito = new Carrito();
                nuevoCarrito.setUsuario(usuario);
                nuevoCarrito.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                
                return carritoRepo.save(nuevoCarrito);
            });
    }
}
