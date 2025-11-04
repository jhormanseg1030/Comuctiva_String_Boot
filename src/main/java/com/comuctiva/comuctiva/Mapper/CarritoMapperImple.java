package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.Dto.Produc_CarriDto;
import com.comuctiva.comuctiva.models.Carrito;
import com.comuctiva.comuctiva.models.Produc_Carri;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class CarritoMapperImple implements CarritoMapper {

    @Override
    public CarritoDto toCarritoDto(Carrito carrito) {
        if (carrito == null) {
            return null;
        }
        
        CarritoDto dto = new CarritoDto();
        dto.setIdCarrito(carrito.getIdCarrito());
        dto.setId_usuario(carrito.getUsuario() != null ? carrito.getUsuario().getId_Usuario() : null);
        
        // ✅ Validar que items no sea null
        if (carrito.getItems() == null || carrito.getItems().isEmpty()) {
            dto.setItems(new ArrayList<>());
            dto.setTotalItems(0);
            dto.setTotal(0.0);
            return dto;
        }
        
        // Mapear items
        dto.setItems(carrito.getItems().stream()
            .map(this::toProduc_CarriDto)
            .collect(Collectors.toList()));
        
        // Calcular total de items
        int totalItems = carrito.getItems().stream()
            .mapToInt(item -> item.getCantidad() != null ? item.getCantidad() : 0)
            .sum();
        dto.setTotalItems(totalItems);
        
        // Calcular total
        double total = carrito.getItems().stream()
            .mapToDouble(item -> {
                if (item.getProd() != null && item.getProd().getValor() != null && item.getCantidad() != null) {
                    return item.getProd().getValor() * item.getCantidad();
                }
                return 0.0;
            })
            .sum();
        dto.setTotal(total);
        
        return dto;
    }

    @Override
    public Produc_CarriDto toProduc_CarriDto(Produc_Carri producCarri) {
        if (producCarri == null) {
            return null;
        }

        Produc_CarriDto dto = new Produc_CarriDto();
        
        // ✅ Validar que el producto no sea null
        if (producCarri.getProd() != null) {
            dto.setIdProducto(producCarri.getProd().getId_producto());
            dto.setNombreProducto(producCarri.getProd().getNomprod());
            dto.setValor(producCarri.getProd().getValor() != null ? producCarri.getProd().getValor() : 0.0);
            dto.setImagen(producCarri.getProd().getImagen());
            dto.setCategoria(producCarri.getProd().getCategoria());
            
            // Calcular subtotal
            double valor = producCarri.getProd().getValor() != null ? producCarri.getProd().getValor() : 0.0;
            int cantidad = producCarri.getCantidad() != null ? producCarri.getCantidad() : 0;
            dto.setSubtotal(valor * cantidad);
        } else {
            // Si el producto es null, poner valores por defecto
            dto.setIdProducto(null);
            dto.setNombreProducto("Producto no disponible");
            dto.setValor(0.0);
            dto.setImagen(null);
            dto.setCategoria("N/A");
            dto.setSubtotal(0.0);
        }
        
        dto.setCantidad(producCarri.getCantidad() != null ? producCarri.getCantidad() : 0);
        
        return dto;
    }
}
