package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.RolDto;
import com.comuctiva.comuctiva.models.Rol;
import com.comuctiva.comuctiva.repositoryes.RolRepositories;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    public RolRepositories rolRepo;

    @GetMapping("/listrol")
    public List<RolDto> getrol(){
        return rolRepo.findAll().stream().map(rol -> {
            RolDto roldto = new RolDto();
            roldto.setId_ro(rol.getId_rol());
            roldto.setNrol(rol.getNom_rol());
            return roldto;
        }).toList();
    }
    @GetMapping
    public List<Rol> getMethodName(){
        return rolRepo.findAll();
    }
    
    
}
