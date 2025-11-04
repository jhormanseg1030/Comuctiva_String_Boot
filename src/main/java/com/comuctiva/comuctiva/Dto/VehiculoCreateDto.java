package com.comuctiva.comuctiva.Dto;

import com.comuctiva.comuctiva.models.EstadoVehiculo;
import com.comuctiva.comuctiva.models.TipoVehiculo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoCreateDto {
    
    @NotNull(message = "El tipo de vehículo es obligatorio")
    private TipoVehiculo tipo;

    @NotBlank(message = "El nombre del vehículo es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "La placa es obligatoria")
    @Pattern(regexp = "^[A-Z]{3}[0-9]{3}$", message = "Formato de placa inválido (Ej: ABC123)")
    private String placa;

    @NotBlank(message = "El conductor es obligatorio")
    @Size(max = 150)
    private String conductor;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 100, message = "La capacidad mínima es 100 kg")
    @Max(value = 10000, message = "La capacidad máxima es 10000 kg")
    private Integer capacidadKg;

    @NotNull(message = "El estado es obligatorio")
    private EstadoVehiculo estado;

    private Boolean mantenimiento;

    @Size(max = 200)
    private String ubicacion;

    @NotNull(message = "La transportadora es obligatoria")
    private Integer id_transportadora;
}
