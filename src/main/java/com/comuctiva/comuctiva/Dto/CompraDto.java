package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompraDto {
    private Integer id_comp;
    private Short tot;
    private String ref_pag;
    private LocalDateTime fec_comp;
    private Integer id_tipago;
    private Integer id_pedi;
}
