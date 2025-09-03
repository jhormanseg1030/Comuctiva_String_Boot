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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Produc_Desc {

    @Embeddable
    public class Produc_DescId implements Serializable{
        private Long descuId;
        private Long produId;
    }
    @EmbeddedId
    private Produc_DescId id = new Produc_DescId();

    @ManyToOne
    @MapsId("descuId")
    private Descuento descuento;

    @ManyToOne
    @MapsId("produId")
    private Producto produ;
}

