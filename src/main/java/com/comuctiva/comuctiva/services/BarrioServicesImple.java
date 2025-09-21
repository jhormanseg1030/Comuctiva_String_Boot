package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.BarrioCreateDto;
import com.comuctiva.comuctiva.Dto.BarrioDto;
import com.comuctiva.comuctiva.Dto.BarrioUpdateDto;
import com.comuctiva.comuctiva.Mapper.BarrioMapper;
import com.comuctiva.comuctiva.models.Barr_Vere;
import com.comuctiva.comuctiva.models.Barrio;
import com.comuctiva.comuctiva.models.Muni;
import com.comuctiva.comuctiva.repositoryes.Barr_VereRepositories;
import com.comuctiva.comuctiva.repositoryes.BarrioRepositories;
import com.comuctiva.comuctiva.repositoryes.MuniRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BarrioServicesImple implements BarrioServices {

    public final BarrioRepositories barrioRepositories;
    public final BarrioMapper barrioMapper;
    public final Barr_VereRepositories barr_VereRepositories;
    public final MuniRepositories muniRepositories;

    public BarrioServicesImple(BarrioRepositories barrioRepositories, BarrioMapper barrioMapper, Barr_VereRepositories barr_VereRepositories, MuniRepositories muniRepositories) {
        this.barrioRepositories = barrioRepositories;
        this.barrioMapper = barrioMapper;
        this.barr_VereRepositories = barr_VereRepositories;
        this.muniRepositories = muniRepositories;
    }

    @Override
    @Transactional
    public BarrioDto crearBarrioDto(BarrioCreateDto barrioCreateDto) {
        Barrio barrio = barrioMapper.toBarrio(barrioCreateDto);
        Barrio barrioGuardado = barrioRepositories.save(barrio);
        return barrioMapper.toBarrioDto(barrioGuardado);
    }

    @Override
    @Transactional()
    public BarrioDto barrioPorId(Integer id) {
        Barrio barrio = barrioRepositories.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado con id:"));
        return barrioMapper.toBarrioDto(barrio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BarrioDto> listartodos() {
        return barrioRepositories.findAll()
            .stream()
            .map(barrioMapper::toBarrioDto)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminarPedidos(Integer id) {
        Barrio barrioElimi = barrioRepositories.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado con id: " + id));
        barrioRepositories.save(barrioElimi);
    }

    @Override
    @Transactional
    public BarrioDto actualizarBarrio(BarrioUpdateDto barrioUpdateDto) {
        Barrio barrio = barrioRepositories.findById(barrioUpdateDto.getId_barr())
            .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado con id: "));

        barrio.setNom(barrioUpdateDto.getNomb());

        Barr_Vere barr_Vere = barr_VereRepositories.findById(barrioUpdateDto.getBarr_verId())
            .orElseThrow(() -> new EntityNotFoundException("Barrio no encontrado con id:"));
        barrio.setBarr_Vere(barr_Vere);

        Muni muni = muniRepositories.findById(barrioUpdateDto.getMuniId())
            .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado con id:"));
        barrio.setMuni(muni);

        Barrio barrioGuardado = barrioRepositories.save(barrio);
        return barrioMapper.toBarrioDto(barrioGuardado);
    }
}
