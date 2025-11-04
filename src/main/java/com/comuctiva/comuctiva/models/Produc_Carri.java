package com.comuctiva.comuctiva.models;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "produc_carri")
public class Produc_Carri {
    
    @EmbeddedId
    private Produc_CarriId id = new Produc_CarriId();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("prodId")
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "FK_producto_carrito"))
    private Producto prod;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("carritoId")
    @JoinColumn(name = "carrito_id", foreignKey = @ForeignKey(name = "FK_producto_carritos"))
    private Carrito carrito;
    
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad = 1;
    
    @Column(name = "fecha_agre", nullable = false, updatable = false)
    private Timestamp fechaAgre = new Timestamp(System.currentTimeMillis());
    
    @Column(name = "nomprod")
    private String nomprod;
}
