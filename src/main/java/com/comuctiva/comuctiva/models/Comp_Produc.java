package com.comuctiva.comuctiva.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "comp_produc")
public class Comp_Produc {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Com_Produc")
    private Integer idComProduc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Compra", referencedColumnName = "ID_Compra")
    private Compra compra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Producto", referencedColumnName = "ID_Producto")
    private Producto produc;

    @NotNull
    private Double valor;
    
    @NotNull
    private Short cant;

    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;
}
