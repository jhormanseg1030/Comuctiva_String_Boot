package com.comuctiva.comuctiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Tipo_PagoDto;
import com.comuctiva.comuctiva.models.Tipo_De_Pago;
import com.comuctiva.comuctiva.repositoryes.Tipo_De_PagoRepositories;

@RestController
@RequestMapping("/api/Tpago")
public class Tipo_PagoController {
    
    @Autowired
    private Tipo_De_PagoRepositories tpagoRepo;

    @GetMapping("/listpag")
    public List<Tipo_PagoDto> gettipago(){
        return tpagoRepo.findAll().stream().map(tipo_pago -> {
            Tipo_PagoDto tpagdto = new Tipo_PagoDto();
            tpagdto.setId_tpag(tipo_pago.getId_tipago());
            tpagdto.setTip(tipo_pago.getTipos());
            return tpagdto;
        }).toList();
    }
    @GetMapping
    public List<Tipo_De_Pago> getMethodName(){
        return tpagoRepo.findAll();
    }

}
