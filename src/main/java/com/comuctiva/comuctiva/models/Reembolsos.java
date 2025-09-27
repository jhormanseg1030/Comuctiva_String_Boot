package com.comuctiva.comuctiva.models;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Compra", nullable = false, foreignKey = @ForeignKey(name = "FK_Rembolso_Compra"))
    private Compra compra;

}
