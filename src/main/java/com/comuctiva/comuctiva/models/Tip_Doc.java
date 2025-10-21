package com.comuctiva.comuctiva.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Tip_Doc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipdocu;
    @Column(length = 30)
    private String tipo;

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
