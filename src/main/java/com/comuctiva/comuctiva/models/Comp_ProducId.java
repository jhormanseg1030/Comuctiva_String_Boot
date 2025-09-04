package com.comuctiva.comuctiva.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
    public class Comp_ProducId {
        private Integer compraId;
        private Long producId;
}
