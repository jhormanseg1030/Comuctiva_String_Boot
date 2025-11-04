package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.CarritoAsignacionDto;
import com.comuctiva.comuctiva.Dto.Produc_CarriDto;
import com.comuctiva.comuctiva.Dto.ProductoAsignado3Dto;
import com.comuctiva.comuctiva.Mapper.Produc_CarriMapper;
import com.comuctiva.comuctiva.models.Produc_Carri;
import com.comuctiva.comuctiva.models.Produc_CarriId;
import com.comuctiva.comuctiva.repositoryes.Produc_CarriRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class Produc_CarriServicesImple implements Produc_CarriServices {

    private final Produc_CarriRepositories repositories; // ✅ CAMBIO: Produc_CarriRepositories → ProducCarriRepositorie
    private final Produc_CarriMapper mapper;

    public Produc_CarriServicesImple(Produc_CarriRepositories repositories, Produc_CarriMapper mapper) {
        this.repositories = repositories;
        this.mapper = mapper;
    }

    @Override
    public Produc_CarriDto asignar(Produc_CarriDto produc_c) {
        Produc_Carri pr = mapper.toProduc_Carri(produc_c);

        // ✅ Crear ID correctamente (orden: prodId, carritoId)
        Produc_CarriId id = new Produc_CarriId();
        id.setProdId(pr.getProd().getId_producto());
        id.setCarritoId(pr.getCarrito().getIdCarrito());
        
        if (repositories.existsById(id)) {
            throw new IllegalStateException("El producto ya está asignado al carrito");
        }
        
        Produc_Carri guardar = repositories.save(pr);
        return mapper.toProduc_CarriDto(guardar);
    }

    @Override
    public List<Produc_CarriDto> listarProduc(Integer producId) {
        return repositories.findByIdProdId(producId)
            .stream()
            .map(mapper::toProduc_CarriDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductoAsignado3Dto> listarProduc2(Integer producId) {
        return repositories.findByIdProdId(producId)
            .stream()
            .map(mapper::toProducAsig3)
            .collect(Collectors.toList());
    }

    @Override
    public List<Produc_CarriDto> listarCarri(Integer carriId) {
        return repositories.findByIdCarritoId(carriId)
            .stream()
            .map(mapper::toProduc_CarriDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<CarritoAsignacionDto> listarCarri2(Integer carriId) {
        return repositories.findByIdCarritoId(carriId)
            .stream()
            .map(mapper::toCarritoAsig)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer producId, Integer carriId) {
        Produc_CarriId id = new Produc_CarriId();
        id.setProdId(producId);
        id.setCarritoId(carriId);
        
        if (!repositories.existsById(id)) {
            throw new EntityNotFoundException("Producto o carrito no encontrado");
        }
        repositories.deleteById(id);
    }
}
