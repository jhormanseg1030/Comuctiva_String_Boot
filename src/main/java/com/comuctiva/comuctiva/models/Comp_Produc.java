package com.comuctiva.comuctiva.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private Integer id_comp_produc;
    
    // ✅ USAR LOS NOMBRES CORRECTOS
    @Column(name = "cant", nullable = false)
    private Short cantidad;
    
    @Column(name = "valor", nullable = false)
    private Double precio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Compra", referencedColumnName = "ID_Compra", nullable = false)
    private Compra compra;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Producto", referencedColumnName = "ID_Producto", nullable = false)
    private Producto producto;
}

