package com.comuctiva.comuctiva.models;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Rembolso {
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Compra", nullable = false, foreignKey = @ForeignKey(name = "FK_Compra_producto"))
    @JoinColumn(name = "id_Produ", nullable = false, foreignKey = @ForeignKey(name = "FK_Producto_compra"))
    private Comp_Produc comp_Produc;

}