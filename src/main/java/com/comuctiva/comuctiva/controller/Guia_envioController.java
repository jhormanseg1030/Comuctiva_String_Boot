package com.comuctiva.comuctiva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Guia_EnvioCrearDtos;
import com.comuctiva.comuctiva.Dto.Guia_EnvioDto;
import com.comuctiva.comuctiva.Dto.Guia_EnvioUpdateDto;
import com.comuctiva.comuctiva.services.Guia_EnvioServices;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/guia_envio")
public class Guia_envioController {
    private final Guia_EnvioServices guia_EnvioServices;

    public Guia_envioController(Guia_EnvioServices guia_EnvioServices) {
        this.guia_EnvioServices = guia_EnvioServices;
    }

    @PostMapping("/crearGuia")
    public ResponseEntity<?> crearGuia(@Valid @RequestBody Guia_EnvioCrearDtos guia_EnvioCrearDtos){
        try{
            Guia_EnvioDto guia_EnvioDto = guia_EnvioServices.crearGuia_Envio(guia_EnvioCrearDtos);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of("mensaje", "Guia de envio creada con exito", "guia_envio", guia_EnvioDto));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("errores1", ex.getMessage()));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error","error al crear una guia de envio", "detalles", ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guia_EnvioDto> consultarPorId(@PathVariable Integer id_guia_envio){
        Guia_EnvioDto guia_EnvioDto = guia_EnvioServices.guia_envioPorId(id_guia_envio);
        return ResponseEntity.ok(guia_EnvioDto);
    }
    
    @GetMapping
    public ResponseEntity<List<Guia_EnvioDto>> listartodo(){
        List<Guia_EnvioDto> guia_EnvioDtos = guia_EnvioServices.listartodos();
        return ResponseEntity.ok(guia_EnvioDtos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Guia_EnvioDto> actualizarGuia(@PathVariable Integer id_guia_envio,
    @Valid @RequestBody Guia_EnvioUpdateDto guienviActualizadoDto){
        guienviActualizadoDto.setId_guia(id_guia_envio);
        Guia_EnvioDto guiaActualizada = guia_EnvioServices.actualizarGuia_Envio(guienviActualizadoDto);
        return ResponseEntity.ok(guiaActualizada);
    }
}
