package com.comuctiva.comuctiva.controller;

import java.util.List;
import com.comuctiva.comuctiva.repositoryes.Unidad_MedidaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.models.Unidad_Medida;

@RestController
@RequestMapping("api/Unidad_Medida")
public class Unidad_MedidaController {

    private final Unidad_MedidaRepositories unidad_MedidaRepositories;

    Unidad_MedidaController(Unidad_MedidaRepositories unidad_MedidaRepositories) {
        this.unidad_MedidaRepositories = unidad_MedidaRepositories;
    }

@GetMapping("/api/unidad_medida")
public List<Unidad_Medida> getMethodName() {
    return unidad_MedidaRepositories.findAll();
}
}
