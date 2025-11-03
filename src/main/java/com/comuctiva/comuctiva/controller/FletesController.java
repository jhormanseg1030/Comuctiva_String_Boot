package com.comuctiva.comuctiva.controller;

import com.comuctiva.comuctiva.Dto.ConfigFletesDto;
import com.comuctiva.comuctiva.services.ConfigFletesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fletes")
public class FletesController {
    
    private final ConfigFletesService configFletesService;
    
    public FletesController(ConfigFletesService configFletesService) {
        this.configFletesService = configFletesService;
    }
    
    @GetMapping("/config")
    public ResponseEntity<ConfigFletesDto> obtenerConfiguracion() {
        ConfigFletesDto config = configFletesService.obtenerConfiguracion();
        return ResponseEntity.ok(config);
    }
}
