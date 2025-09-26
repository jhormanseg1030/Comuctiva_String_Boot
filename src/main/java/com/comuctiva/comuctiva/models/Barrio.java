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
@Data
@Entity
public class Barrio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_barrio;
    @Column (length=50)
    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Muni", nullable = false, foreignKey = @ForeignKey(name = "FK_Muni"))
    private Muni muni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Barr_vere", nullable = false, foreignKey = @ForeignKey(name = "Fk_Barr_Vere"))
    private Barr_Vere barr_Vere;
}
