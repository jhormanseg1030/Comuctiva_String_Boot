package com.comuctiva.comuctiva.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rol {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_Rol;

@Column(nullable = false, length = 30)
private String Nom_Rol;

public Integer getID_Rol(){
    return ID_Rol;
}
}
