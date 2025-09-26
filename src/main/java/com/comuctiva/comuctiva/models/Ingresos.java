package com.comuctiva.comuctiva.models;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ingresos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ingreso;
    @Column(length = 50)
    private String orbser;
    private Timestamp fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_Usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_Usuar"))
    private Usuario usuario;

    @OneToMany(mappedBy = "ingresos")
    private List<Ingres_Produc> produc;
}
