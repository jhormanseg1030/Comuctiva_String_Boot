package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.ObserDto;
import com.comuctiva.comuctiva.models.Obser;
import com.comuctiva.comuctiva.repositoryes.ObserRepositories;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/obser")
public class ObserController {

    @Autowired
    private ObserRepositories obserRepo;

    @GetMapping("/listobser")
    public List<ObserDto> getobsers(){
        return obserRepo.findAll().stream().map(observaciones ->{
            ObserDto obserdto = new ObserDto();
            obserdto.setId_ob(observaciones.getId_obser());
            obserdto.setObse(observaciones.getObser());
            return obserdto;
        }).toList();
    }
    @GetMapping
    public List<Obser> getMethodName(){
        return obserRepo.findAll();
    }
    
    
}
