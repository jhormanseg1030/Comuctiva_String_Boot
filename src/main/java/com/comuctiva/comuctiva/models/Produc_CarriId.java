package com.comuctiva.comuctiva.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = {"prodId","carritoId"})
public class Produc_CarriId implements Serializable{
    public Produc_CarriId (Integer id_producto, Integer id_carrito){
    }
    private Long prodId;
    private Long carritoId;
}

