package com.comuctiva.comuctiva.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"compraId","producId"})
    public class Comp_ProducId {
        public Comp_ProducId(Integer id_compra, Integer id_producto){

        }
        private Long compraId;
        private Long producId;
}
