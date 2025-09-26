package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.EstadoDto;
import com.comuctiva.comuctiva.models.Estado;
import com.comuctiva.comuctiva.repositoryes.EstadoRepositories;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoRepositories estaRepo;

    @GetMapping("/listest")
    public List<EstadoDto> getestados(){
        return estaRepo.findAll().stream().map(estado -> {
            EstadoDto estadto = new EstadoDto();
            estadto.setId_est(estado.getId_estado());
            estadto.setEst(estado.getEstado());
            return estadto;
        }).toList();
    }
    @GetMapping
    public List<Estado> getMethodName(){
        return estaRepo.findAll();
    }
}
