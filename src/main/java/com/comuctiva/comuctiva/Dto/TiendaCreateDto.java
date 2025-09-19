package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TiendaCreateDto {

    @NotBlank
    private String nomti;
    private String loogo;

    @NotNull
    private Integer direccId;
}
