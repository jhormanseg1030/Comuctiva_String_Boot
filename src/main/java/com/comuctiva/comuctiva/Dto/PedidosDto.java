package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidosDto {
    private Integer id_pe;
    private LocalDateTime fh_p;
}
