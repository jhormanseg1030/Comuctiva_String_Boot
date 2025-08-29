package com.comuctiva.comuctiva.Dto;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarritoDto {
private Integer id_carrito;
private String can;
private Timestamp fec_agre;
}
