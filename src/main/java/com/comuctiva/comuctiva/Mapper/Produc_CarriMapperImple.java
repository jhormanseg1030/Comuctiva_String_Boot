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

@Component
public class Produc_CarriMapperImple implements Produc_CarriMapper{
    private final ProductoRepositorie producRepo;
    private final CarritoRepositories carriRepo;

    public Produc_CarriMapperImple(ProductoRepositorie producRepo, CarritoRepositories carriRepo){
        this.producRepo = producRepo;
        this.carriRepo = carriRepo;
    }

    @Override
    public Produc_Carri toProduc_Carri(Produc_CarriDto produc_CarriDto){
        Producto producto = producRepo.findById(produc_CarriDto.getId_producto())
        .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con el id"));

        Carrito carrito = carriRepo.findById(produc_CarriDto.getId_carro())
        .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado con el id"));
        Produc_CarriId id = new Produc_CarriId(produc_CarriDto.getId_producto(),produc_CarriDto.getId_carro());

        Produc_Carri pc = new Produc_Carri();
        pc.setId(id);
        pc.setProd(producto);
        pc.setCarrito(carrito);
        return pc;
    }

    @Override
    public Produc_CarriDto toProduc_CarriDto(Produc_Carri produc_Carri){
        return new Produc_CarriDto(
            produc_Carri.getProd().getId_producto(),
            produc_Carri.getCarrito().getId_carrito(),
            produc_Carri.getProd().getNomprod());
    }

    @Override
    public ProductoAsignado3Dto toProducAsig3(Produc_Carri produc){
        ProductoAsignado3Dto dto = new ProductoAsignado3Dto();
        dto.setNombre_prod(produc.getNomprod());
        return dto;
    }

    @Override
    public CarritoAsignacionDto toCarritoAsig(Produc_Carri carri){
        CarritoAsignacionDto dto = new CarritoAsignacionDto();
        dto.setId_carro(carri.getCarrito().getId_carrito());
        return dto;
    }
}
