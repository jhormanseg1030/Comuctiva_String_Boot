package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Guia_EnvioCrearDtos {

    @NotBlank
    private String fech_en;

    @NotBlank
    private Integer transpId;
    private Integer obserId;
}
