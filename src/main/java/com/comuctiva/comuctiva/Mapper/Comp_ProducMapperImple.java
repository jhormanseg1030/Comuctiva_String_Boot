package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.Dto.Compra_AsignacionesDto;
import com.comuctiva.comuctiva.Dto.Producto_AsignacionesDto;
import com.comuctiva.comuctiva.models.Comp_Produc;
import com.comuctiva.comuctiva.models.Compra;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.repositoryes.CompraRepositories;
import com.comuctiva.comuctiva.repositoryes.ProductoRepositorie;

import jakarta.persistence.EntityNotFoundException;

@Component
public class Comp_ProducMapperImple implements Comp_producMapper {

    private final CompraRepositories compraRepositories;
    private final ProductoRepositorie productoRepositorie;
    
    public Comp_ProducMapperImple(CompraRepositories compraRepositories, ProductoRepositorie productoRepositorie) {
        this.compraRepositories = compraRepositories;
        this.productoRepositorie = productoRepositorie;
    }

    @Override
    public Comp_Produc toComp_Produc(Com_ProducDto comp_producDto) {
        Compra compra = compraRepositories.findById(comp_producDto.getId_compra())
        .orElseThrow(()-> new EntityNotFoundException("Compra no encontrada con el id"));

        Producto producto = productoRepositorie.findById(comp_producDto.getId_producto())
        .orElseThrow(()-> new EntityNotFoundException("Producto no encontrada con el id"));

        Comp_Produc cp = new Comp_Produc();
        // Ya no se usa setId porque ahora es auto-generado
        cp.setValor(comp_producDto.getValor());
        cp.setCant(comp_producDto.getCantidad());
        cp.setFechaAsignacion(comp_producDto.getFechaAsignacion());
        cp.setCompra(compra);
        cp.setProduc(producto);
        return cp;
    }

    @Override
    public Com_ProducDto toCom_ProducDto(Comp_Produc comp_produc) {
        return new Com_ProducDto(
            comp_produc.getCompra().getId_compra(),
            comp_produc.getProduc().getId_producto(),
            comp_produc.getProduc().getNomprod(),
            comp_produc.getCant(),
            comp_produc.getValor(),
            comp_produc.getFechaAsignacion()
        );
    }

    @Override
    public Compra_AsignacionesDto toCompra_AsignacionesDto(Comp_Produc comp){
        Compra_AsignacionesDto dto= new Compra_AsignacionesDto();
        dto.setId_compra(comp.getCompra().getId_compra());
        dto.setFechaAsignacion(comp.getFechaAsignacion());
        dto.setCantidad(comp.getCant());
        dto.setValor(comp.getValor());
        return dto;
    }

    @Override
    public Producto_AsignacionesDto toProducto_AsignacionesDto(Comp_Produc prod){
        Producto_AsignacionesDto dto = new Producto_AsignacionesDto();
        dto.setNombre_Producto(prod.getProduc().getNomprod());
        dto.setFechaAsignacion(prod.getFechaAsignacion());
        dto.setCantidad(prod.getCant());
        dto.setValor(prod.getValor());
        return dto;
    }
}
