package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Com_ProducCreateDto {
    
    @NotNull
    private Integer id_producto;
    
    @NotNull
    @Positive
    private Short cantidad;
    
    @NotNull
    @Positive
    private Double precio;
}