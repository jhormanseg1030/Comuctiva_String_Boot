package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoUpdateDto {
    @NotNull
    private Integer id_pedi;
    private LocalDateTime fechor_pedi;
    private Integer usuarioId;
    private Integer guiaenviId;
    private Integer estadoId;
}
