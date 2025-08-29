package com.comuctiva.comuctiva.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id_ingresos","id_producto"
})
public class Ingres_ProducId implements Serializable{
    public Ingres_ProducId(Long id, Long id2 ){
    }
    private Integer id_ingresos;
    private Integer id_producto;
}
