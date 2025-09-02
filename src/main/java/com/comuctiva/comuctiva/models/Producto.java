package com.comuctiva.comuctiva.models;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Integer id_producto;

    private String nomprod;
    private Double valor;
    private Short cant;

    @Column (nullable = false, length = 50)
    private String imagen;

    @Column (nullable = false, length = 50)
    private String descrip;

    /*
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingres_Produc> ingresos = new ArrayList<>();*/
}
