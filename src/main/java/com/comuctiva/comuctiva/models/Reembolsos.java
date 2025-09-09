package com.comuctiva.comuctiva.models;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reembolsos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Rembolso;
    private LocalDateTime fec_Soli;
    private Long valor;
    @Column(nullable = false, length = 100)
    private String motivo;
    private LocalDateTime fec_Resp;
    @Column(length = 20)
    private String estado;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "compra_id", referencedColumnName = "compra_id", foreignKey = @ForeignKey(name = "FK_reembolso_compra")),
        @JoinColumn(name = "producto_id", referencedColumnName = "producto_id", foreignKey = @ForeignKey(name = "FK_reembolso_producto"))
    })
    private Comp_Produc compProduc;
}
