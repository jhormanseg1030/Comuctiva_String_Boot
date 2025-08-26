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
public class Obser {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_Obser;

@Column(nullable = false,length = 40)
private String Obser;

public Integer getID_Obser(){
    return ID_Obser;
}
}
