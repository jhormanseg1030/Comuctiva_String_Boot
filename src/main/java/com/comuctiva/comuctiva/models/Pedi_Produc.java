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
@Entity
@Data
public class Pedi_Produc {
    private Short cant;
    private Double valor;

    @EmbeddedId
    private Pedi_producId id = new Pedi_producId();

    @ManyToOne
    @MapsId("pedidosId")
    @JoinColumn(name = "ID_Pedido", foreignKey = @ForeignKey(name = "FK_producto_pedidos"))
    private Pedidos pedi;

    @ManyToOne
    @MapsId("proId")
    @JoinColumn(name = "ID_Producto", foreignKey = @ForeignKey(name = "FK_pedidos_producto"))
    private Producto productos;
}
