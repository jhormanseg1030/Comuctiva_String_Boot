package com.comuctiva.comuctiva.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Cotizacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cotizacion;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @NotBlank(message = "El producto es obligatorio")
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String producto;

    @NotNull(message = "El peso es obligatorio")
    @Min(value = 1, message = "El peso mínimo es 1 kg")
    @Column(name = "peso_kg", nullable = false)
    private Double pesoKg;

    @NotNull(message = "El tipo de vehículo es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false, length = 20)
    private TipoVehiculo tipoVehiculo;

    @NotBlank(message = "El origen es obligatorio")
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String destino;

    @NotNull(message = "La distancia es obligatoria")
    @Min(value = 1, message = "La distancia mínima es 1 km")
    @Column(name = "distancia_km", nullable = false)
    private Double distanciaKm;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoCotizacion estado;

    @Embedded
    private DetallesCotizacion detalles;

    @NotNull(message = "El total es obligatorio")
    @Column(nullable = false)
    private Double total;

    @Size(max = 500)
    @Column(name = "motivo_rechazo", length = 500)
    private String motivoRechazo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transpor", foreignKey = @ForeignKey(name = "fk_cotizacion_transportadora"))
    private Transportadora transportadora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_Cotizacion_Usuario"))
    private Usuario usuario;
    
    @PrePersist
    protected void onCreate() {
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
        if (estado == null) {
            estado = EstadoCotizacion.PENDIENTE;
        }
    }
}
