package com.comuctiva.comuctiva.models;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    private String nomprod;
    private Double valor;
    private Short cant;

    @Column (nullable = false, length = 50)
    private String imagen;

    @Column (nullable = false, length = 50)
    private String descrip;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "ID_Tienda", nullable = false, foreignKey = @ForeignKey(name = "FK_Tienda"))
    private Tienda tienda;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingres_Produc> ingre = new ArrayList<>();
}
