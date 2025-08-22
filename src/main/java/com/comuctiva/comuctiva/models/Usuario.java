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
@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Usuario;
    @Column(length = 50)
    private String nom_Usu;
    @Column( nullable = false ,length = 50)
    private String apell1;
    @Column(length = 50)
    private String apell2;
    private Long tel;
    @Column(nullable = false)
    private Long tel2;
    @Column(length = 50)
    private String correo;
    private Byte numDoc;
    private String password;
}
