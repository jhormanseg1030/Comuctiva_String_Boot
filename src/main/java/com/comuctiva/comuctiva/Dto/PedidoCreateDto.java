package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoCreateDto {
    @NotBlank
    private LocalDateTime fechor_pedi;
    @NotNull
    private Integer usuarioId;
    private Integer guiaenviId;
    private Integer estadoId;
}
