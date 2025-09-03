package com.comuctiva.comuctiva.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Rol {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;
    @Column(nullable = false, length = 30)
    private String nom_rol;

    @OneToMany(mappedBy ="rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rol_Usuario> usuario = new ArrayList<>();
}
