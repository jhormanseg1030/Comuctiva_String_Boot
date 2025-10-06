package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DireccionesCreateDto {
    @NotBlank
    private String nume;
    private String compl;
    private String ubic_geo;

    @NotNull
    private Integer barrioId;
    private Integer usuariosId;
    private Integer viasId; 
    
}
