package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Tip_DocDto;
import com.comuctiva.comuctiva.models.Tip_Doc;
import com.comuctiva.comuctiva.repositoryes.Tip_DocRepositories;


@RestController
@RequestMapping("/api/tipdoc")
public class Tip_DocController {

    @Autowired
    private Tip_DocRepositories tipdocRepo;

    @GetMapping("/tipdocList")
    public List<Tip_DocDto> gettip_doc(){
        return tipdocRepo.findAll().stream().map(tip_doc -> {
            Tip_DocDto tipdocdto = new Tip_DocDto();
            tipdocdto.setId_tipdo(tip_doc.getId_tipdocu());
            tipdocdto.setTipo(tip_doc.getTipo());
            return tipdocdto;
        }).toList();
    }
    @GetMapping
    public List<Tip_Doc> getMethodName(){
        return tipdocRepo.findAll();
    }
    
}
