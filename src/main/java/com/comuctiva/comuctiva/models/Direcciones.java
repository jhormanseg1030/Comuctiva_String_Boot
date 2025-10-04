package com.comuctiva.comuctiva.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Entity
@Data
public class Direcciones {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_direcc;
    @Column(length=10)
    private String num;
    @Column(length=50)
    private String comple;
    @Column(length=20)
    private String ubi_geo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Barrio", nullable = false, foreignKey = @ForeignKey(name = "FK_Barrio"))
    private Barrio barrio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Vias", nullable = false, foreignKey = @ForeignKey(name = "Fk_Vias"))
    private Vias vias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_Usua"))
    private Usuario usuario;
}
