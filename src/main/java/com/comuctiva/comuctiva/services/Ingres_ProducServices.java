package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.Ingres_ProducDto;
import com.comuctiva.comuctiva.Dto.IngresosAsigDto;
import com.comuctiva.comuctiva.Dto.ProducAsig4Dto;


public interface Ingres_ProducServices {

    Ingres_ProducDto asignar(Ingres_ProducDto ingresPro);

    List<Ingres_ProducDto> listarIngresos(Integer ingresoId);

    List<IngresosAsigDto> listarIngresos2(Integer ingresoId);

    List<Ingres_ProducDto> listarProducto(Integer productoId);

    List<ProducAsig4Dto>listarProducto2(Integer productoId);

    void eliminar(Integer ingresoId, Integer productoId);
}
