package com.comuctiva.comuctiva.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Produc_Carri {
    @EmbeddedId
    private Produc_CarriId id = new Produc_CarriId();

    @ManyToOne
    @MapsId("prodId")
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "FK_producto_carrito"))
    private Producto prod;

    @ManyToOne
    @MapsId ("carritoId")
    @JoinColumn(name = "carrito_id", foreignKey = @ForeignKey(name = "FK_producto_carritos"))
    private Carrito carrito;
    
    private String nomprod;
}
