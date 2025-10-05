package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompraCreateDto {

    @NotBlank
    private Double total;
    private String referencia_pago;
    private LocalDateTime fecha_compra;

    @NotNull
    private Integer id_tipago;
    private Integer id_pedido;
}
