
package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Barr_VereDto;
import com.comuctiva.comuctiva.models.Barr_Vere;
import com.comuctiva.comuctiva.repositoryes.Barr_VereRepositories;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/barrvere")
public class Barr_VereController {

    @Autowired
    private Barr_VereRepositories barrvereRepo;

    @GetMapping("/listbarrvere")
    public List<Barr_VereDto> getbarrvere(){
        return barrvereRepo.findAll().stream().map(barr_vere -> {
            Barr_VereDto barrveredto = new Barr_VereDto();
            barrveredto.setId_vere(barr_vere.getId_vere());
            barrveredto.setNomb(barr_vere.getNombre());
            return barrveredto;
        }).toList();
    }
    @GetMapping
    public List<Barr_Vere> getMethodName(){
        return barrvereRepo.findAll();
    }
}
