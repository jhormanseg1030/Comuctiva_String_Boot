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
public class Unidad_Medida {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_Medida;
    @Column (length=20)
    private String tip_Medida;

    public Integer getId_Medida(){
        return id_Medida;
    }

    public String getTip_Medida(){
        return tip_Medida;
    }
}
