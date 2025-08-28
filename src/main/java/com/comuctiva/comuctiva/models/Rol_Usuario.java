package com.comuctiva.comuctiva.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Rol_Usuario {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Boolean estado;

}
