package com.comuctiva.comuctiva.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotNull;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("compraId")
    @JoinColumn(name = "compra_id", foreignKey = @ForeignKey(name = "FK_producto_compra"), referencedColumnName = "id_compra" )
    private Compra compra;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productoId")
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "FK_compra_producto"), referencedColumnName = "id_producto")
    private Producto produc;

    @NotNull
    private Double valor;
    @NotNull
    private Short cant;

    @NotNull
    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;

}
