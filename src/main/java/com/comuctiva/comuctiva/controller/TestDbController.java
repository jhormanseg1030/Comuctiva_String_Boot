package com.comuctiva.comuctiva.controller;

import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestDbController {

    @Autowired
    private UsuarioRepositories usuarioRepositories;

    @GetMapping("/db")
    public String testDbConnection() {
        long total = usuarioRepositories.count();
        return "Conexión exitosa. Total usuarios: " + total;
    }
}
