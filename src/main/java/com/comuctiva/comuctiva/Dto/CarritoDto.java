package com.comuctiva.comuctiva.Dto;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarritoDto {
private Integer ID_Carrito;
private String Cantidad;
private Timestamp Fecha_Agre;
}
