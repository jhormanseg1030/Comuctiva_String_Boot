package com.comuctiva.comuctiva.Dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngresosDto {
    private Integer id_ingre;
    private String  obser;
    private Timestamp fech;

}
