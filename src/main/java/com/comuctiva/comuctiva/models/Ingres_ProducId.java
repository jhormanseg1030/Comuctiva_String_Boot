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
@EqualsAndHashCode(of = {"ingresoId","productoId"
})
public class Ingres_ProducId implements Serializable{
    public Ingres_ProducId(Integer id_ingreso, Integer id_producto ){
    }
    private Long ingresoId;
    private Long productoId;
}
