package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.TransportadoraDto;
import com.comuctiva.comuctiva.models.Transportadora;
import com.comuctiva.comuctiva.repositoryes.TransportadoraRepositorie;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/transportadora")
public class TransportadoraController{

    @Autowired
    private TransportadoraRepositorie traspoRepo;

    @GetMapping("/listransportadora")
    public List<TransportadoraDto> gettransportadora(){
        return traspoRepo.findAll().stream().map(transportadora -> {
            TransportadoraDto transdto = new TransportadoraDto();
            transdto.setId_trans(transportadora.getId_transpor());
            transdto.setLog(transportadora.getLogo());
            transdto.setTelefo(transportadora.getTelefono());
            transdto.setNombt(transportadora.getNombret());
            transdto.setCorreo(transportadora.getCorreo());
            transdto.setDirec(transportadora.getDirecc());
            transdto.setSiti_web(transportadora.getSitio_web());
            return transdto;
        }).toList();
    }
    @GetMapping
    public List<Transportadora> getMethodName(){
        return traspoRepo.findAll();
    }
    
    
}
