package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

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
    public Carrito toCarrito(CarritoDto carritoDto){
        Carrito carrito = new Carrito();
        carrito.setId_carrito(carritoDto.getId_carrit());
        carrito.setCantidad(carritoDto.getCan());
        carrito.setFecha_agre(carritoDto.getFec_agre());

        Usuario usuario = usuarioRepositories.findById(carritoDto.getUsuId())
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
    @Override
    public List<CarritoDto> toCarritoDtoList(List<Carrito>carritos){
        if (carritos== null) {
            return List.of();
        }
        List<CarritoDto>carritoDtos=new ArrayList<CarritoDto>(carritos.size());
        for(Carrito carrito : carritos){
            carritoDtos.add(toCarritoDto(carrito));
        }
        return carritoDtos;
    }
    @Override
    public void updateCarrito(Carrito carrito, CarritoDto carritoDto){
        if (carritoDto == null ) {
            return;
        }
        carrito.setId_carrito(carritoDto.getId_carrit());
        carrito.setCantidad(carritoDto.getCan());
        carrito.setFecha_agre(carritoDto.getFec_agre());
    }
}
