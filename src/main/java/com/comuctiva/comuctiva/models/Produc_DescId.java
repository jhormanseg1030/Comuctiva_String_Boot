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
@EqualsAndHashCode(of = {"descuId","produId"})
public class Produc_DescId implements Serializable {
    public Produc_DescId(Integer id_Descu, Integer id_producto ){
    }
    private Long descuId;
    private Long produId;
}
