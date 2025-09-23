package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.CarritoCreateDto;
import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.models.Carrito;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class CarritoMapperImple implements CarritoMapper{

    private final UsuarioRepositories usuarioRepositories;

    public CarritoMapperImple(UsuarioRepositories usuarioRepositories){
        this.usuarioRepositories=usuarioRepositories;
    }
    @Override
    public Carrito toCarrito(CarritoCreateDto carritoCreateDto){
        if (carritoCreateDto == null) {
            return null;
        }
        Carrito carrito = new Carrito();
        carrito.setCantidad(carritoCreateDto.getCan());
        carrito.setFecha_agre(carritoCreateDto.getFec_agre());

        Usuario usuario = usuarioRepositories.findById(carritoCreateDto.getUsuId())
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        carrito.setUsuario(usuario);
        return carrito;
    }

    @Override
    public CarritoDto toCarritoDto(Carrito carrito){
        return new CarritoDto(
            carrito.getId_carrito(),
            carrito.getCantidad(),
            carrito.getFecha_agre(),
            carrito.getUsuario().getId_Usuario());
        
    }
}
