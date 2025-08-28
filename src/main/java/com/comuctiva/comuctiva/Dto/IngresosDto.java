package com.comuctiva.comuctiva.Dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngresosDto {
    private Integer Id_Ingre;
    private String  Obser;
    private Timestamp Fech;

}
