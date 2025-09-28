package com.comuctiva.comuctiva.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Com_ProducDto {

    @NotNull(message = "El ID de la compra no puede ser nulo")
    private Integer id_compra;

    @NotNull(message = "El ID del producto no puede ser nulo")
    private Integer id_producto;
    private String nombre_Producto;

    private Short cantidad;
    private Double valor;

    @NotNull(message = "La fecha de asignación es obligatoria")
    private LocalDate fechaAsignacion;
}
