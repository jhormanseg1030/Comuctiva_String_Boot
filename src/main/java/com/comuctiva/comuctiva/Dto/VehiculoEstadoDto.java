package com.comuctiva.comuctiva.Dto;

import com.comuctiva.comuctiva.models.EstadoVehiculo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoEstadoDto {
    
    @NotNull(message = "El estado es obligatorio")
    private EstadoVehiculo estado;

    @Size(max = 200)
    private String ubicacion;

    private Boolean mantenimiento;
}
