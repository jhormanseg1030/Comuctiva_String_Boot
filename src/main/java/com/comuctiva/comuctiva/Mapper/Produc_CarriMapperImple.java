package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.CarritoAsignacionDto;
import com.comuctiva.comuctiva.Dto.Produc_CarriDto;
import com.comuctiva.comuctiva.Dto.ProductoAsignado3Dto;
import com.comuctiva.comuctiva.models.Carrito;
import com.comuctiva.comuctiva.models.Produc_Carri;
import com.comuctiva.comuctiva.models.Produc_CarriId;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.repositoryes.CarritoRepositories;
import com.comuctiva.comuctiva.repositoryes.ProductoRepositorie;

import jakarta.persistence.EntityNotFoundException;

import java.sql.Timestamp;

@Component
public class Produc_CarriMapperImple implements Produc_CarriMapper {
    
    private final ProductoRepositorie producRepo;
    private final CarritoRepositories carriRepo;

    public Produc_CarriMapperImple(ProductoRepositorie producRepo, CarritoRepositories carriRepo) {
        this.producRepo = producRepo;
        this.carriRepo = carriRepo;
    }

    @Override
    public Produc_Carri toProduc_Carri(Produc_CarriDto produc_CarriDto) {
        Producto producto = producRepo.findById(produc_CarriDto.getIdProducto())
            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + produc_CarriDto.getIdProducto()));

        Carrito carrito = carriRepo.findById(produc_CarriDto.getId_carro())
            .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado con id: " + produc_CarriDto.getId_carro()));
        
        Produc_CarriId id = new Produc_CarriId();
        id.setProdId(produc_CarriDto.getIdProducto());
        id.setCarritoId(produc_CarriDto.getId_carro());

        Produc_Carri pc = new Produc_Carri();
        pc.setId(id);
        pc.setProd(producto);
        pc.setCarrito(carrito);
        pc.setCantidad(produc_CarriDto.getCantidad() != null ? produc_CarriDto.getCantidad() : 1);
        pc.setNomprod(producto.getNomprod());
        pc.setFechaAgre(new Timestamp(System.currentTimeMillis()));
        
        return pc;
    }

    @Override
    public Produc_CarriDto toProduc_CarriDto(Produc_Carri produc_Carri) {
        if (produc_Carri == null) {
            return null;
        }
        
        Produc_CarriDto dto = new Produc_CarriDto();
        
        // ✅ Mapear todos los campos del DTO actualizado
        if (produc_Carri.getProd() != null) {
            dto.setIdProducto(produc_Carri.getProd().getId_producto());
            dto.setNombreProducto(produc_Carri.getProd().getNomprod());
            dto.setValor(produc_Carri.getProd().getValor() != null ? produc_Carri.getProd().getValor() : 0.0);
            dto.setImagen(produc_Carri.getProd().getImagen());
            dto.setCategoria(produc_Carri.getProd().getCategoria());
            
            // ✅ Calcular subtotal
            double valor = produc_Carri.getProd().getValor() != null ? produc_Carri.getProd().getValor() : 0.0;
            int cantidad = produc_Carri.getCantidad() != null ? produc_Carri.getCantidad() : 0;
            dto.setSubtotal(valor * cantidad);
        }
        
        if (produc_Carri.getCarrito() != null) {
            dto.setId_carro(produc_Carri.getCarrito().getIdCarrito());
        }
        
        dto.setCantidad(produc_Carri.getCantidad() != null ? produc_Carri.getCantidad() : 1);
        
        return dto;
    }

    @Override
    public ProductoAsignado3Dto toProducAsig3(Produc_Carri produc) {
        if (produc == null) {
            return null;
        }
        
        ProductoAsignado3Dto dto = new ProductoAsignado3Dto();
        dto.setNombre_prod(produc.getNomprod());
        return dto;
    }

    @Override
    public CarritoAsignacionDto toCarritoAsig(Produc_Carri carri) {
        if (carri == null || carri.getCarrito() == null) {
            return null;
        }
        
        CarritoAsignacionDto dto = new CarritoAsignacionDto();
        dto.setId_carro(carri.getCarrito().getIdCarrito());
        return dto;
    }
}