package com.comuctiva.comuctiva.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Descu;
    @Column (length = 50)
    private String descripcion;
    private LocalDateTime fech_des;
    private BigDecimal valor;

    @OneToMany(mappedBy = "descuento")
    private List<Produc_Desc> producto ;
}