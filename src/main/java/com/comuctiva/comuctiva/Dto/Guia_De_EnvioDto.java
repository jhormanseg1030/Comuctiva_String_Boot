package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Guia_De_EnvioDto {
    private Integer id_gui;
    private String fech_en;
    private Integer transpId;
    private Integer obserId;
}
