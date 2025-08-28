package com.comuctiva.comuctiva.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Calilficaciones_produc {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id_calificaciones;

@Column(length = 50)
private String comentario;
@Column()
private LocalDateTime fecha_calificacion;
@Column()
private Short estrellas;
}
