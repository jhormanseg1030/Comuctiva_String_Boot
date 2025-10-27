package com.comuctiva.comuctiva.models;

import java.util.List;

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

    @OneToMany(mappedBy = "rol")
    private List<Rol_Usuario> roles_de_usuarios;

    public void setNom_rol(String nom_rol) {
        this.nom_rol = nom_rol;
    }
}
