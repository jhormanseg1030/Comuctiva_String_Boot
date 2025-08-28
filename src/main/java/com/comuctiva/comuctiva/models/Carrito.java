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
private Integer id_carrito;

@Column (length=10)
private Integer id_producto;


@Column (length=10)
private Integer cantidad;
@Column ()
private Timestamp fecha_agre;
}
