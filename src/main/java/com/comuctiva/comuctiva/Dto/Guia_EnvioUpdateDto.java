package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Guia_EnvioUpdateDto {

    @NotNull
    private Integer id_guia;
    
    @NotBlank
    private String fech_en;

    @NotNull
    private Integer transpId;
    private Integer obserId;
}
