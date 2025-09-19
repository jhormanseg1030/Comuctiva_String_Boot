package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TiendaDto {
    private Integer id_ti;
    private String nomti;
    private String loogo;
    private Integer direccId;
}
