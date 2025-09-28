package com.comuctiva.comuctiva.Dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Producto_AsignacionesDto {
    private String nombre_Producto;
    private LocalDate fechaAsignacion;
    private Short cantidad;
    private Double valor;
}
