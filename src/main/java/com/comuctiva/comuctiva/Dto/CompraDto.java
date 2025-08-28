package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompraDto {
    private Integer id_compra;
    private Short total;
    private String ref_pago;
    private LocalDateTime fec_com;
}
