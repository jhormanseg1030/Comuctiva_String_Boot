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
public class Ingres_Produc {
    @EmbeddedId
    private Ingres_ProducId id = new Ingres_ProducId();

    @ManyToOne
    @MapsId("ingresoId")
    @JoinColumn(name = "ingreso_id", foreignKey = @ForeignKey(name = "FK_producto_ingreso"))
    private Ingresos ingresos;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "FK_producto_ingresos2"))
    private Producto producto;
    private Short cant;
    private Double valor;
}
