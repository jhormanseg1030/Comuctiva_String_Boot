package com.comuctiva.comuctiva.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comp_Produc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_com_produc;
    private Short cant;
    private Double valor;
}
