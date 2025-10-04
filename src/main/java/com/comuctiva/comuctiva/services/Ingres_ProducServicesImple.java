package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.Ingres_ProducDto;
import com.comuctiva.comuctiva.Dto.IngresosAsigDto;
import com.comuctiva.comuctiva.Dto.ProducAsig4Dto;
import com.comuctiva.comuctiva.Mapper.Ingres_ProducMapper;
import com.comuctiva.comuctiva.models.Ingres_Produc;
import com.comuctiva.comuctiva.models.Ingres_ProducId;
import com.comuctiva.comuctiva.repositoryes.Ingres_ProducRepositories;

@Service
public class Ingres_ProducServicesImple implements Ingres_ProducServices {

    private final Ingres_ProducRepositories ingresProRepositories;
    private final Ingres_ProducMapper ingresProMapper;

    public Ingres_ProducServicesImple(Ingres_ProducRepositories ingresProRepositories, Ingres_ProducMapper ingresProMapper) {
        this.ingresProRepositories = ingresProRepositories;
        this.ingresProMapper = ingresProMapper;
    }

    @Override
    public Ingres_ProducDto asignar(Ingres_ProducDto ingresPro){
        Ingres_Produc ip = ingresProMapper.toIngres_ProducDto(ingresPro);

        Ingres_ProducId id = new Ingres_ProducId(ip.getIngresos().getId_ingreso(), ip.getProducto().getId_producto());
        if (ingresProRepositories.existsById(id)) {
            throw new IllegalArgumentException("Ya existe un ingreso asignado a este producto");
        }
        Ingres_Produc guardar = ingresProRepositories.save(ip);
        return ingresProMapper.toIngresProDto(guardar);
    }

    @Override
    public List<Ingres_ProducDto> listarIngresos(Integer ingresoId) {
        return ingresProRepositories.findByIdIngresoId(ingresoId)
        .stream()
        .map(ingresProMapper::toIngresProDto)
        .collect(Collectors.toList());
    }

    @Override
    public List<IngresosAsigDto> listarIngresos2(Integer ingresoId) {
        return ingresProRepositories.findByIdIngresoId(ingresoId)
        .stream()
        .map(ingresProMapper::toIngresAsig)
        .collect(Collectors.toList());
    }

    @Override
    public List<Ingres_ProducDto> listarProducto(Integer productoId) {
        return ingresProRepositories.findByIdProductoId(productoId)
        .stream()
        .map(ingresProMapper::toIngresProDto)
        .collect(Collectors.toList());
    }

    @Override
    public List<ProducAsig4Dto> listarProducto2(Integer productoId) {
        return ingresProRepositories.findByIdProductoId(productoId)
        .stream()
        .map(ingresProMapper::toproAsig)
        .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer ingresoId, Integer productoId) {
        Ingres_ProducId id = new Ingres_ProducId(ingresoId, productoId);
        if (!ingresProRepositories.existsById(id)) {
            throw new IllegalArgumentException("Ingreso o Producto no encontrado");
        }
        ingresProRepositories.deleteById(id);
    }
}
