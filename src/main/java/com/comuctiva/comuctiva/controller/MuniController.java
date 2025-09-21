package com.comuctiva.comuctiva.controller;

import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.MuniDto;
import com.comuctiva.comuctiva.services.MuniServices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/muni")
public class MuniController {

    private final MuniServices muniServices;

    public MuniController(MuniServices muniServices) {
        this.muniServices = muniServices;
    }
    @GetMapping("/municipios")
    public ResponseEntity<List<MuniDto>> listartodos(){
        List<MuniDto> municipios = muniServices.listartodos();
        return ResponseEntity.ok(municipios);
    }
}
