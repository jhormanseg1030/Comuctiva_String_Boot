package com.comuctiva.comuctiva.Dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Compra_AsignacionesDto {
    private Integer id_compra;
    private LocalDate fechaAsignacion;
    private Short cantidad;
    private Double valor;
}
