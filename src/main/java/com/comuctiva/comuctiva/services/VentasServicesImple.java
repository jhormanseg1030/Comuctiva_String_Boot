package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.VentaDto;
import com.comuctiva.comuctiva.Mapper.VentaMapper;
import com.comuctiva.comuctiva.repositoryes.Comp_ProductRepositories;

@Service
public class VentasServicesImple implements VentasServices {
    
    private final Comp_ProductRepositories compProducRepositories;
    private final VentaMapper ventaMapper;

    public VentasServicesImple(Comp_ProductRepositories compProducRepositories, VentaMapper ventaMapper) {
        this.compProducRepositories = compProducRepositories;
        this.ventaMapper = ventaMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<VentaDto> obtenerMisVentas(Integer idUsuario) {
        return compProducRepositories.findVentasByVendedor(idUsuario).stream()
            .map(ventaMapper::toVentaDto)
            .collect(Collectors.toList());
    }
        @Override
    public List<VentaDto> listarTodas() {
        return compProducRepositories.findAll().stream()
            .map(ventaMapper::toVentaDto)
            .collect(Collectors.toList());
    }
}
