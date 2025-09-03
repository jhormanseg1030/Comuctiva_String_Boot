package com.comuctiva.comuctiva.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
    @Embeddable
    public class Produc_CarriId implements Serializable{
        private Long prodId;
        private Long carritoId;
    }
    @EmbeddedId
    private Produc_CarriId id = new Produc_CarriId();

    @ManyToOne
    @MapsId("prodId")
    private Producto prod;

    @ManyToOne
    @MapsId ("carritoId")
    private Carrito carrito;
}
