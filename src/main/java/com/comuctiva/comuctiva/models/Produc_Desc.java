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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Produc_Desc {

    @EmbeddedId
    private Produc_DescId id = new Produc_DescId();

    @ManyToOne
    @MapsId("descuId")
    @JoinColumn(name = "descuentos_id")
    private Descuento descuento;

    @ManyToOne
    @MapsId("produId")
    @JoinColumn(name = "productos_id", foreignKey = @ForeignKey(name = "FK_producto_descuento"))
    private Producto produ;
}

