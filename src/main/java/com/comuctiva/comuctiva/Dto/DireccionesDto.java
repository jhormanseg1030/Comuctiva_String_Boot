package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DireccionesDto {
    private Integer id_direc;
    private String nume;
    private String compl;
    private String ubic_geo;

}
