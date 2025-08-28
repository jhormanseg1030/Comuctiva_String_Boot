package com.comuctiva.comuctiva.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Pedidos {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_pedido;
    private LocalDateTime fehor_pedi;
}
