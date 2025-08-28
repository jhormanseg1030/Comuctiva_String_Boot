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
public class calificaciones_produc {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_Calificaciones;

@Column(length = 50)
private String Comentario;
@Column()
private LocalDateTime Fecha_Calificacion;
@Column()
private Short Estrellas;
}
