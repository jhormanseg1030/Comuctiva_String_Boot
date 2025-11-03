package com.comuctiva.comuctiva.Dto;

import com.comuctiva.comuctiva.models.EstadoCotizacion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CotizacionEstadoDto {
    
    @NotNull(message = "El estado es obligatorio")
    private EstadoCotizacion estado;

    @Size(max = 500)
    private String motivoRechazo;
}
