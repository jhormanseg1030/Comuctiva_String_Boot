package com.comuctiva.comuctiva.Dto;

import com.comuctiva.comuctiva.models.TipoVehiculo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CotizacionCreateDto {
    
    @NotBlank(message = "El producto es obligatorio")
    @Size(max = 200)
    private String producto;

    @NotNull(message = "El peso es obligatorio")
    @Min(value = 1)
    private Integer pesoKg;

    @NotNull(message = "El tipo de vehículo es obligatorio")
    private TipoVehiculo tipoVehiculo;

    @NotBlank(message = "El origen es obligatorio")
    @Size(max = 200)
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    @Size(max = 200)
    private String destino;

    @NotNull(message = "La distancia es obligatoria")
    @Min(value = 1)
    private Integer distanciaKm;

    private Boolean urgente;

    private Integer id_transportadora;
    
    private Integer id_usuario;
}
