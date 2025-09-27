package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Unidad_MedidaDto;
import com.comuctiva.comuctiva.models.Unidad_Medida;
import com.comuctiva.comuctiva.repositoryes.Unidad_MedidaRepositories;


@RestController
@RequestMapping("api/Unidad_Medida")
public class Unidad_MedidaController {

    @Autowired
    private Unidad_MedidaRepositories unidad_medidaRepo;

    @GetMapping("/unidad")
    public List<Unidad_MedidaDto> getUnidad() {
        return unidad_medidaRepo.findAll().stream().map(unidad ->{
            Unidad_MedidaDto dto = new Unidad_MedidaDto();
            dto.setId_Unidad(unidad.getId_Medida());
            dto.setTipo_de_medida(unidad.getTip_Medida());
            return dto;
        }).toList();
    }
    @GetMapping
    public List<Unidad_Medida> getMethodName() {
        return unidad_medidaRepo.findAll();
    }
}
