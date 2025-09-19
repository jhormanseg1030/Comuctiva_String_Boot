package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BarrioUpdateDto {
    @NotNull
    private Integer id_barr;

    @NotBlank
    private String nomb;

    @NotNull
    private Integer barr_verId;
    private Integer muniId;
}
