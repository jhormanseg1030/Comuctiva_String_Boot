package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.ViasDto;
import com.comuctiva.comuctiva.models.Vias;
import com.comuctiva.comuctiva.repositoryes.ViasRepositories;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/vias")
public class ViasController {
    @Autowired
    private ViasRepositories viRepo;

    @GetMapping("/listvias")
    public List<ViasDto> getvias(){
        return viRepo.findAll().stream().map(vias -> {
            ViasDto vidto = new ViasDto();
            vidto.setId_vi(vias.getId_vias());
            vidto.setTips(vias.getTipos());
            return vidto;
        }).toList();
    }
    @GetMapping
    public List<Vias> getMethodName(){
        return viRepo.findAll();
    }
}
