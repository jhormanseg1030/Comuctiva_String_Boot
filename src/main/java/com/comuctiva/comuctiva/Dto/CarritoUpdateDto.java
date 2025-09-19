package com.comuctiva.comuctiva.Dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CarritoUpdateDto {
    @NotNull
    private Integer id_carrit;

    @NotBlank
    private String can;
    private Timestamp fec_agre;

    @NotNull
    private Integer usuId;
}
