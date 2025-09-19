package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CalificacionUpdateDto {
    @NotNull
    private Integer id_califi;
    private Integer id_produ;
    private Integer id_usua;

    @NotBlank
    private String coment;
    private LocalDateTime fec_calif;
    private Short estre;
}
