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
public class Comp_Produc {
    @EmbeddedId
    private Comp_ProducId id = new Comp_ProducId();

    @ManyToOne
    @MapsId("compraId")
    @JoinColumn(name = "compra_id", foreignKey = @ForeignKey(name = "FK_producto_compra"))
    private Compra compra;

    @ManyToOne
    @MapsId("producId")
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "FK_compra_producto"))
    private Producto produc;

    private Short valor;
    private Short cant;
}
