package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.DescuentosDto;
import com.comuctiva.comuctiva.models.Descuento;
import com.comuctiva.comuctiva.repositoryes.DescuentoRepositories;

@RestController
@RequestMapping("/api/descuentos")
public class DescuentosController {

    @Autowired
    private DescuentoRepositories descuRepo;

    @GetMapping("/listdes")
    public List<DescuentosDto> getdescuentos(){
        return  descuRepo.findAll().stream().map(descuentos -> {
            DescuentosDto desdto = new DescuentosDto();
            desdto.setId_descu(descuentos.getId_Descu());
            desdto.setDescrip(descuentos.getDescripcion());
            desdto.setFec_des(descuentos.getFech_des());
            desdto.setVal(descuentos.getValor());
            return desdto;
        }).toList();
    }
    @GetMapping
    public List<Descuento> getMethodName(){
        return descuRepo.findAll();
    }

}
