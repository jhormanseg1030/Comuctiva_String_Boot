package com.comuctiva.comuctiva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.Califi_ProduCreateDto;
import com.comuctiva.comuctiva.Dto.Califi_ProduDto;
import com.comuctiva.comuctiva.Dto.CalificacionUpdateDto;
import com.comuctiva.comuctiva.services.Califi_ProducServices;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/califi_produc")
public class Califi_ProducController {

    private final Califi_ProducServices califi_ProducServices;

    public Califi_ProducController(Califi_ProducServices califi_ProducServices) {
        this.califi_ProducServices = califi_ProducServices;
    }

    @PostMapping
    public ResponseEntity<?> crearCalifi_Produc(@Valid @RequestBody Califi_ProduCreateDto califi_ProduCreateDto) {
        try{
            Califi_ProduDto califi_Produ = califi_ProducServices.crearCalif_Produ(califi_ProduCreateDto);
            return ResponseEntity.status(201)
            .body(Map.of("mensaje", "Califi_Produ created with éxito", "detalles", califi_Produ));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(400)
            .body(Map.of("mensaje",ex.getMessage()));
        }catch(Exception ex){
            return ResponseEntity.status(500)
            .body(Map.of("mensaje", "Error al crear una califi_Produ", "detalles", ex.getMessage()));
        }
    }
    
    @GetMapping("/{id_califi}")
    public ResponseEntity<Califi_ProduDto> obtenerId(@PathVariable Integer id_califi) {
        Califi_ProduDto califi_Produ = califi_ProducServices.califi_ProduPorId(id_califi);
        return ResponseEntity.ok(califi_Produ);
    }
    
    @GetMapping
    public ResponseEntity<List<Califi_ProduDto>>listar(){
        List<Califi_ProduDto> califi_Produ = califi_ProducServices.listar();
        return ResponseEntity.ok(califi_Produ);
    }

    @PutMapping("/{id_califi}")
    public ResponseEntity<Califi_ProduDto> putActualizar(@PathVariable Integer id_califi, @RequestBody CalificacionUpdateDto califiUpdate) {
        califiUpdate.setId_califi(id_califi);
        Califi_ProduDto actualizado = califi_ProducServices.actualizarCalif_Produ(califiUpdate);
        return ResponseEntity.ok(actualizado);
    }
}
