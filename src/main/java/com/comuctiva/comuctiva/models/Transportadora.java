package com.comuctiva.comuctiva.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Transportadora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_transpor;

    @Column (nullable = false, length = 30)
    private String logo;

    @Column (nullable = false, length = 20)
    private Long telefono;

    @Column (nullable = false, length = 30)
    private String nombret;

    @Column (nullable = false, length = 50)
    private String correo;

    @Column (nullable = false, length = 50)
    private String direcc;

    @Column (nullable = false, length = 50)
    private String sitio_web;
}
