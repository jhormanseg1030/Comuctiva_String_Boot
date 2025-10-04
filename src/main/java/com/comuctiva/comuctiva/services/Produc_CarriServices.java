package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.CarritoAsignacionDto;
import com.comuctiva.comuctiva.Dto.Produc_CarriDto;
import com.comuctiva.comuctiva.Dto.ProductoAsignado3Dto;

public interface Produc_CarriServices {

    Produc_CarriDto asignar(Produc_CarriDto produc_c);

    List<Produc_CarriDto> listarProduc(Integer producId);

    List<ProductoAsignado3Dto> listarProduc2(Integer producId);

    List<Produc_CarriDto> listarCarri(Integer carriId);

    List<CarritoAsignacionDto> listarCarri2(Integer carriId);
    
    void eliminar(Integer producId, Integer carriId);

}
