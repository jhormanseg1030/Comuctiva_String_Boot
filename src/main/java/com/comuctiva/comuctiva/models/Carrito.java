package com.comuctiva.comuctiva.models;

import java.security.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Carrito {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id_carrito;
@Column (length=10)
private String cantidad;
private Timestamp fecha_agre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_Usuari"))
    private Usuario usuario;
}
