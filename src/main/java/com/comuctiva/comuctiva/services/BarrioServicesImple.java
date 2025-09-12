package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.BarrioDto;
import com.comuctiva.comuctiva.Mapper.BarrioMapper;
import com.comuctiva.comuctiva.models.Barrio;
import com.comuctiva.comuctiva.repositoryes.BarrioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BarrioServicesImple implements BarrioServices {

    public final BarrioRepositories barrioRepositories;
    public final BarrioMapper barrioMapper;

    public BarrioServicesImple(BarrioRepositories barrioRepositories, BarrioMapper barrioMapper) {
        this.barrioRepositories = barrioRepositories;
        this.barrioMapper = barrioMapper;
    }

    @Override
    public BarrioDto crearBarrioDto(BarrioDto barrioDto) {
        Barrio barrio = barrioMapper.toBarrio(barrioDto);
        Barrio barrioGuardado = barrioRepositories.save(barrio);
        return barrioMapper.toBarrioDto(barrioGuardado);
    }

    @Override
    public BarrioDto barrioPorId(Integer id) {
        return barrioRepositories.findById(id)
            .map(barrioMapper::toBarrioDto)
            .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado con id: " + id));
    }

    @Override
    public List<BarrioDto> listartodos() {
        return barrioRepositories.findAll()
            .stream()
            .map(barrioMapper::toBarrioDto)
            .collect(Collectors.toList());
    }

    @Override
    public void updateBarrio(Integer id, BarrioDto barrioDto) {
        Barrio barrioExistente = barrioRepositories.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado con id: " + id));
        barrioMapper.updateBarrio(barrioExistente, barrioDto);
        barrioRepositories.save(barrioExistente);
    }
}
