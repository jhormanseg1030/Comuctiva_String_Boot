package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.TiendaCreateDto;
import com.comuctiva.comuctiva.Dto.TiendaDto;
import com.comuctiva.comuctiva.Dto.TiendaUpdateDto;
import com.comuctiva.comuctiva.Mapper.TiendaMapper;
import com.comuctiva.comuctiva.models.Direcciones;
import com.comuctiva.comuctiva.models.Tienda;
import com.comuctiva.comuctiva.repositoryes.DireccionesRepositories;
import com.comuctiva.comuctiva.repositoryes.TiendaRepositories;

@Service
public class TiendaServicesImple implements TiendaServices {
    
    private final TiendaRepositories tiendaRepositories;
    private final TiendaMapper tiendaMapper;
    private final DireccionesRepositories direccionesRepositories;
    
    public TiendaServicesImple(TiendaRepositories tiendaRepositories, TiendaMapper tiendaMapper, DireccionesRepositories direccionesRepositories) {
        this.tiendaRepositories = tiendaRepositories;
        this.tiendaMapper = tiendaMapper;
        this.direccionesRepositories = direccionesRepositories;
    }

    @Override
    @Transactional
    public TiendaDto crearTienda(TiendaCreateDto tiendaCreateDto) {
        Tienda tienda = tiendaMapper.toTienda(tiendaCreateDto);
        Tienda tiendaGuardado = tiendaRepositories.save(tienda);
        return tiendaMapper.toTiendaDto(tiendaGuardado);
    }

    @Override
    @Transactional()
    public TiendaDto tiendaPorId(Integer id) {
        Tienda tienda = tiendaRepositories.findById(id)
            .orElseThrow(() -> new IllegalStateException("Tienda no encontrada con id:"));
        return tiendaMapper.toTiendaDto(tienda);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TiendaDto> listartodos() {
        return tiendaRepositories.findAll()
            .stream()
            .map(tiendaMapper::toTiendaDto)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminarTienda(Integer id) {
        Tienda tiendaElimi = tiendaRepositories.findById(id)
            .orElseThrow(() -> new IllegalStateException("Tienda no encontrada con id: " + id));
        tiendaRepositories.save(tiendaElimi);
    }

    @Override
    @Transactional
    public TiendaDto actualizarTienda(TiendaUpdateDto tiendaUpdateDto) {
        Tienda tienda = tiendaRepositories.findById(tiendaUpdateDto.getId_ti())
            .orElseThrow(() -> new IllegalStateException("Tienda no encontrada con id: "));

        tienda.setNombreT(tiendaUpdateDto.getNomti());
        tienda.setLog(tiendaUpdateDto.getLoogo());

        Direcciones direcciones = direccionesRepositories.findById(tiendaUpdateDto.getDireccId())
            .orElseThrow(() -> new IllegalStateException("Direcciones no encontrada con id:"));
        tienda.setDirecciones(direcciones);

        Tienda tiendaGuardado = tiendaRepositories.save(tienda);
        return tiendaMapper.toTiendaDto(tiendaGuardado);
    }
}
