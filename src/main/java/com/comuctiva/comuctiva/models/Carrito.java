package com.comuctiva.comuctiva.models;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Carrito {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_Carrito;
@Column (length=10)
private Integer Cantidad;
@Column ()
private Timestamp Fecha_Agre;
}
