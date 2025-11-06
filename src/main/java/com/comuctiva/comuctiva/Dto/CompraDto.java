package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompraDto {
    private Integer id_compra;
    private Double total;
    private String ref_pago;
    private LocalDateTime fec_com;
    private Integer id_pedido;
    private Integer id_ti_pago;
    private String tipo_pago;
    private List<Com_ProducDto> productos;
}
