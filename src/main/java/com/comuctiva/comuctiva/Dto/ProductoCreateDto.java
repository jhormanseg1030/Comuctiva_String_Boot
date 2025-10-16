package com.comuctiva.comuctiva.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoCreateDto {
    @NotBlank
    private String nombre_Producto;
    private Double valor;
    private Short cantidad;
    private String imagen;
    private String descripcion;

    @NotNull
    private Integer id_medida;
}
