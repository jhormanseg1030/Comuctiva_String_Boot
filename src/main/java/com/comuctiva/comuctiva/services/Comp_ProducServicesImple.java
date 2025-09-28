package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.Dto.Compra_AsignacionesDto;
import com.comuctiva.comuctiva.Dto.Producto_AsignacionesDto;
import com.comuctiva.comuctiva.Mapper.Comp_producMapper;
import com.comuctiva.comuctiva.models.Comp_Produc;
import com.comuctiva.comuctiva.models.Comp_ProducId;
import com.comuctiva.comuctiva.repositoryes.Comp_ProductRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Comp_ProducServicesImple implements Comp_ProducServices {

    private final Comp_ProductRepositories repositories;
    private final Comp_producMapper mapper;

    public Comp_ProducServicesImple(Comp_ProductRepositories repositories, Comp_producMapper mapper) {
        this.repositories = repositories;
        this.mapper = mapper;
    }

    @Override
    public Com_ProducDto asignar(Com_ProducDto com_p) {
        Comp_Produc cp = mapper.toComp_Produc(com_p);

        Comp_ProducId id = new Comp_ProducId(cp.getCompra().getId_compra(), cp.getProduc().getId_producto());
        if (repositories.existsById(id)) {
            
            throw new IllegalStateException("El producto ya esta asignado a la compra");
        }
        Comp_Produc guardar = repositories.save(cp);
        return mapper.toCom_ProducDto(guardar);
    }

    @Override
    public List<Com_ProducDto>listarCompra(Integer compraId) {
        return repositories.findByIdCompraId(compraId)
        .stream()
        .map(mapper::toCom_ProducDto)
        .collect(Collectors.toList());
    }
    
    @Override
    public List<Compra_AsignacionesDto> listarCompra2(Integer compraId) {
        return repositories.findByIdCompraId(compraId)
        .stream()
        .map(mapper::toCompra_AsignacionesDto)
        .collect(Collectors.toList());
    }

    @Override
    public List<Com_ProducDto> listarProducto(Integer productoId) {
        return repositories.findByIdProductoId(productoId)
        .stream()
        .map(mapper::toCom_ProducDto)
        .collect(Collectors.toList());
    }

    @Override
    public List<Producto_AsignacionesDto> listarProducto2(Integer productoId) {
        return repositories.findByIdProductoId(productoId)
        .stream()
        .map(mapper::toProducto_AsignacionesDto)
        .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer compraId, Integer productoId) {
        Comp_ProducId id = new Comp_ProducId(compraId, productoId);
        if (!repositories.existsById(id)) {
            throw new EntityNotFoundException("compra o producto no encontrado");
        }
        repositories.deleteById(id);
    }
}
