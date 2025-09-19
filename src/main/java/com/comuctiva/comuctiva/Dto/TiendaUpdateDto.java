package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TiendaUpdateDto {

    @NotNull
    private Integer id_ti;
    private Integer direccId;

    @NotBlank
    private String nomti;
    private String loogo;
}
