package com.comuctiva.comuctiva.controller;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.BarrioCreateDto;
import com.comuctiva.comuctiva.Dto.BarrioDto;
import com.comuctiva.comuctiva.Dto.BarrioUpdateDto;
import com.comuctiva.comuctiva.services.BarrioServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/barrio")
public class BarrioController {

    private final BarrioServices barrioServices;

    public BarrioController(BarrioServices barrioServices) {
        this.barrioServices = barrioServices;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearBarrio( @Valid @RequestBody BarrioCreateDto barrioCreateDto) {
        try{
            BarrioDto barrioDto = barrioServices.crearBarrioDto(barrioCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of("mensaje", "Barrio creado con exito", "Detalles", barrioDto));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("mensaje",ex.getMessage()));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error","error al crear un barrio", "detalles", ex.getMessage()));
        }
    }
    
    @GetMapping("/{id_barr}")
    public ResponseEntity<BarrioDto> obtenerporId(@PathVariable Integer id_barr){
        BarrioDto barrio = barrioServices.barrioPorId(id_barr);
        return ResponseEntity.ok(barrio);
    }
    
    @GetMapping
    public ResponseEntity<List<BarrioDto>> listar(){
        List<BarrioDto> barrios = barrioServices.listartodos();
        return ResponseEntity.ok(barrios);
    }

    @PutMapping("/{id_barr}")
    public ResponseEntity<BarrioDto> putActualizar(@PathVariable Integer id_barr,@RequestBody BarrioUpdateDto barrioUp ) {
        barrioUp.setId_barr(id_barr);
        BarrioDto actualizado = barrioServices.actualizarBarrio(barrioUp);
        return ResponseEntity.ok(actualizado);
    }
}
