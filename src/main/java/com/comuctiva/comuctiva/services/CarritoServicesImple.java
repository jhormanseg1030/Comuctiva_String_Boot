package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.Mapper.CarritoMapper;
import com.comuctiva.comuctiva.models.Carrito;
import com.comuctiva.comuctiva.repositoryes.CarritoRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarritoServicesImple implements CarritoServices {

    private final CarritoRepositories carritoRepositories;
    private final CarritoMapper carritoMapper;

    public CarritoServicesImple(CarritoRepositories carritoRepositories, CarritoMapper carritoMapper) {
        this.carritoRepositories = carritoRepositories;
        this.carritoMapper = carritoMapper;
    }

    @Override
    public CarritoDto crearCarrito(CarritoDto carritoDto) {
        Carrito carrito = carritoMapper.toCarrito(carritoDto);
        Carrito carritoGuardado = carritoRepositories.save(carrito);
        return carritoMapper.toCarritoDto(carritoGuardado);
    }

    @Override
    public CarritoDto carritoPorId(Integer id) {
        return carritoRepositories.findById(id)
                .map(carritoMapper::toCarritoDto)
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
    }

    @Override
    public List<CarritoDto> listartodos() {
        return carritoRepositories.findAll()
                .stream()
                .map(carritoMapper::toCarritoDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarCarrito(Integer id) {
        carritoRepositories.deleteById(id);
    }
}
