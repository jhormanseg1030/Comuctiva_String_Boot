package com.comuctiva.comuctiva.Dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IngresosCreateDto {
    @NotBlank
    private String obser;
    private Timestamp fech;
    @NotNull
    private Integer id_usu;
}
