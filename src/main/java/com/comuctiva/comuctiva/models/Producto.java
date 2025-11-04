package com.comuctiva.comuctiva.models;

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
@Entity
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;

    private String nomprod;
    private Double valor;
    private Short cant;

    @Column (nullable = false, length = 50)
    private String imagen;

    @Column (nullable = false, length = 50)
    private String descrip;

    @Column (length = 50)
    private String categoria;

    @OneToMany(mappedBy = "producto")
    private List<Ingres_Produc> ingre;

    @OneToMany(mappedBy = "produc")
    private List<Comp_Produc> compras;

    @OneToMany(mappedBy = "produ")
    private List<Produc_Desc> descu;

    @OneToMany(mappedBy = "prod")
    private List<Produc_Carri> carrito;

    @OneToMany(mappedBy = "productos")
    private List<Pedi_Produc> productos_de_pedidos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Medida", nullable = false, foreignKey = @ForeignKey(name = "FK_Unidad_Medida"))
    private Unidad_Medida unidad_Medida;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Usuario vendedor;

    @Column(name = "activo", columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean activo = true;
}
