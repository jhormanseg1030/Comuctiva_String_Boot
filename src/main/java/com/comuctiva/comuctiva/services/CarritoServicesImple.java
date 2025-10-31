package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.CarritoCreateDto;
import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.Dto.CarritoUpdateDto;
import com.comuctiva.comuctiva.Mapper.CarritoMapper;
import com.comuctiva.comuctiva.models.Carrito;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.CarritoRepositories;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarritoServicesImple implements CarritoServices {

    private final CarritoRepositories carritoRepositories;
    private final CarritoMapper carritoMapper;
    private final UsuarioRepositories usuarioRepositories;

    public CarritoServicesImple(CarritoRepositories carritoRepositories, CarritoMapper carritoMapper, UsuarioRepositories usuarioRepositories) {
        this.carritoRepositories = carritoRepositories;
        this.carritoMapper = carritoMapper;
        this.usuarioRepositories = usuarioRepositories;
    }

    @Override
    @Transactional
    public CarritoDto crearCarrito(CarritoCreateDto carritoCreateDto) {
        Carrito carrito = carritoMapper.toCarrito(carritoCreateDto);
        Carrito carritoGuardado = carritoRepositories.save(carrito);
        return carritoMapper.toCarritoDto(carritoGuardado);
    }

    @Override
    @Transactional()
    public CarritoDto carritoPorId(Integer id_carrit) {
        Carrito carrito = carritoRepositories.findById(id_carrit)
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
                return carritoMapper.toCarritoDto(carrito);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarritoDto> listartodos() {
        return carritoRepositories.findAll()
                .stream()
                .map(carritoMapper::toCarritoDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarCarrito(Integer id_carrit) {
        Carrito carritoElimi = carritoRepositories.findById(id_carrit)
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado con id: " + id_carrit));
        carritoRepositories.delete(carritoElimi);
    }

    @Override
    @Transactional
    public CarritoDto actualizarCarrito(CarritoUpdateDto carritoUpdateDto) {
        Carrito carrito = carritoRepositories.findById(carritoUpdateDto.getId_carrit())
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado con id: "));

        carrito.setCantidad(carritoUpdateDto.getCan());
        carrito.setFecha_agre(carritoUpdateDto.getFec_agre());

        Usuario usuario = usuarioRepositories.findById(carritoUpdateDto.getUsuId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        carrito.setUsuario(usuario);

        Carrito carritoGuardado = carritoRepositories.save(carrito);
        return carritoMapper.toCarritoDto(carritoGuardado);
    }
}
