package com.comuctiva.comuctiva.Dto;

import com.comuctiva.comuctiva.models.EstadoVehiculo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoUpdateDto {
    
    @NotNull(message = "El ID es obligatorio")
    private Integer id_vehiculo;

    @Size(max = 100)
    private String nombre;

    @Size(max = 150)
    private String conductor;

    private EstadoVehiculo estado;

    private Boolean mantenimiento;

    @Size(max = 200)
    private String ubicacion;

    @Min(value = 0)
    private Integer viajesMes;

    @Min(value = 0)
    private Double ingresosMes;
}
