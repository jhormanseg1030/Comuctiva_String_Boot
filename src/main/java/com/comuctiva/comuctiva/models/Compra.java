package com.comuctiva.comuctiva.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Compra {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_compra;
    private Short total;
    @Column (length = 30)
    private String ref_pago;
    private LocalDateTime fec_com;
}
