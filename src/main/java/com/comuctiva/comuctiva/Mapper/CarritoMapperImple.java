package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.CarritoDto;
import com.comuctiva.comuctiva.models.Carrito;

@Component
public class CarritoMapperImple implements CarritoMapper{

    @Override
    public Carrito toCarrito(CarritoDto carritoDto){
        if (carritoDto == null) {
            return null;
        }
        Carrito carrito= new Carrito();
        carrito.setId_carrito(carritoDto.getId_carrit());
        carrito.setCantidad(carritoDto.getCan());
        carrito.setFecha_agre(carritoDto.getFec_agre());
        return carrito;
}
    @Override
    public CarritoDto toCarritoDto(Carrito carrito){
        if (carrito == null) {
            return null;
        }
        CarritoDto carritoDto = new CarritoDto();
        carritoDto.setId_carrit(carrito.getId_carrito());
        carritoDto.setCan(carrito.getCantidad());
        carritoDto.setFec_agre(carrito.getFecha_agre());
        return carritoDto;
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
