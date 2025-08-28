package com.comuctiva.comuctiva.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Muni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_muni;
    @Column(length = 30)
    private String nom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Dep", nullable = false, foreignKey = @ForeignKey(name = "Fk_Departamento"))
    private Departamento departamento;
}
