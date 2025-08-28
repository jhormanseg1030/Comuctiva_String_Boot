package com.comuctiva.comuctiva.models;

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
@Entity
@Data
public class Direcciones {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_Direcc;
@Column(length=10)
private String num;
@Column(length=50)
private String comple;
@Column(length=20)
private String Ubi_Geo;
}
