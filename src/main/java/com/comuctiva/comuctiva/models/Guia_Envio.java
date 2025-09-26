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
public class Guia_Envio {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_guia;
    @Column (length=50)
    private String fec_env;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Transpor", nullable = false, foreignKey = @ForeignKey(name = "FK_Transpor"))
    private Transportadora transportadora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Obser", nullable = false, foreignKey = @ForeignKey(name = "FK_Obser"))
    private Obser obser;
}
