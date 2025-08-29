package com.comuctiva.comuctiva.models;

import java.sql.Timestamp;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ingresos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ingresos;
    @Column(length = 50)
    private String orbser;
    private Timestamp fecha;

    @OneToMany(mappedBy = "ingresos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingres_Produc> productos = new ArrayList<>();
}
