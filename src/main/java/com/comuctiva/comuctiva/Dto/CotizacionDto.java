package com.comuctiva.comuctiva.Dto;

import com.comuctiva.comuctiva.models.EstadoCotizacion;
import com.comuctiva.comuctiva.models.TipoVehiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CotizacionDto {
    private Integer id_cotizacion;
    private LocalDateTime fecha;
    private String producto;
    private Integer pesoKg;
    private TipoVehiculo tipoVehiculo;
    private String origen;
    private String destino;
    private Integer distanciaKm;
    private EstadoCotizacion estado;
    private DetallesCotizacionDto detalles;
    private Double total;
    private String motivoRechazo;
    private Integer id_transportadora;
    private String nombreTransportadora;
    private Integer id_usuario;
    private String nombreUsuario;
}
