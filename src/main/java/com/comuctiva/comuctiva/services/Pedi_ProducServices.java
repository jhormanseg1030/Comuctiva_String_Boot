package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.Pedi_ProducDto;
import com.comuctiva.comuctiva.Dto.PedidosAsignadosDto;
import com.comuctiva.comuctiva.Dto.ProductoAsignado2Dto;

public interface Pedi_ProducServices {

    Pedi_ProducDto asignar(Pedi_ProducDto pedi_p);

    List<Pedi_ProducDto> listarPedi(Integer pedidosId);

    List<PedidosAsignadosDto> listarPedi2(Integer pedidosId);

    List<Pedi_ProducDto> listarProduc(Integer proId);

    List<ProductoAsignado2Dto> listarProduc2AsignadosDtos(Integer proId);

    void eliminar(Integer pedidosId, Integer proId);

}
