package com.comuctiva.comuctiva.Dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarritoDto {
private Integer id_carrit;
private String can;
private Timestamp fec_agre;
private Integer usuId;
}
