package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.DepartamentoDto;
import com.comuctiva.comuctiva.models.Departamento;
import com.comuctiva.comuctiva.repositoryes.DepartamentoRepositories;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepositories departaRepo;

    @GetMapping("/listdepart")
    public List<DepartamentoDto> getdepartamento(){
        return departaRepo.findAll().stream().map(departamento -> {
            DepartamentoDto departdto = new DepartamentoDto();
            departdto.setId_dep(departamento.getId_Dep());
            departdto.setNdep(departamento.getNom_Dep());
            return departdto;
        }).toList();
    }
    @GetMapping
    public List<Departamento> getMethodName(){
        return departaRepo.findAll();
    }
    
    
}
