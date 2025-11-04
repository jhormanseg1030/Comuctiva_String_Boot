package com.comuctiva.comuctiva.Dto;

import com.comuctiva.comuctiva.models.EstadoVehiculo;
import com.comuctiva.comuctiva.models.TipoVehiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoDto {
    private Integer id_vehiculo;
    private TipoVehiculo tipo;
    private String nombre;
    private String placa;
    private String conductor;
    private Integer capacidadKg;
    private EstadoVehiculo estado;
    private Boolean mantenimiento;
    private String ubicacion;
    private Integer viajesMes;
    private Double ingresosMes;
    private Integer id_transportadora;
    private String nombreTransportadora;
}
