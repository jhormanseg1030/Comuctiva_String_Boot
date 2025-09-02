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
public class Comp_Produc {
    @Embeddable
    public class Comp_ProducId implements Serializable  {
        private Long compraId;
        private Long producId;
    }
    @EmbeddedId
    private Comp_ProducId id = new Comp_ProducId();

    @ManyToOne
    @MapsId("compraId")
    private Compra compra;

    @ManyToOne
    @MapsId("producId")
    private Producto produc;

    private Short valor;
    private Short cant;
}
