package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class Califi_ProduCreateDto {

    @NotBlank
    private String coment;
    private LocalDateTime fec_calif;
    private Short estre;

    @NotNull
    private Integer id_produ;
    private Integer id_usua;
}
