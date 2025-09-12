package com.comuctiva.comuctiva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.MuniDto;
import com.comuctiva.comuctiva.services.MuniServices;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/muni")
public class MuniController {

    private final MuniServices muniServices;

    public MuniController(MuniServices muniServices) {
        this.muniServices = muniServices;
    }
    @PostMapping("/crear")
    public ResponseEntity<MuniDto> crearMuni(@Valid @RequestBody MuniDto muniDto) {
        MuniDto entity = muniServices.crearMuniDto(muniDto);
        return ResponseEntity.ok(entity);
    }
}
