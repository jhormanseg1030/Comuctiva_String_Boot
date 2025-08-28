package com.comuctiva.comuctiva.Dto;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarritoDto {
private Integer Id_carrito;
private String Can;
private Timestamp Fec_agre;
}
