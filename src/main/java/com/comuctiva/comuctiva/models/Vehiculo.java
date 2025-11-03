package com.comuctiva.comuctiva.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Vehiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_vehiculo;

    @NotNull(message = "El tipo de vehículo es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoVehiculo tipo;

    @NotBlank(message = "El nombre del vehículo es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La placa es obligatoria")
    @Pattern(regexp = "^[A-Z]{3}[0-9]{3}$", message = "Formato de placa inválido (Ej: ABC123)")
    @Column(nullable = false, length = 10, unique = true)
    private String placa;

    @NotBlank(message = "El conductor es obligatorio")
    @Size(max = 150, message = "El nombre del conductor no puede exceder 150 caracteres")
    @Column(nullable = false, length = 150)
    private String conductor;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 100, message = "La capacidad mínima es 100 kg")
    @Max(value = 10000, message = "La capacidad máxima es 10000 kg")
    @Column(nullable = false)
    private Integer capacidadKg;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoVehiculo estado;

    @Column(nullable = false)
    private Boolean mantenimiento = false;

    @Size(max = 200, message = "La ubicación no puede exceder 200 caracteres")
    @Column(length = 200)
    private String ubicacion;

    @Min(value = 0, message = "Los viajes no pueden ser negativos")
    @Column(nullable = false)
    private Integer viajesMes = 0;

    @Min(value = 0, message = "Los ingresos no pueden ser negativos")
    @Column(nullable = false)
    private Double ingresosMes = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transportadora", nullable = false, foreignKey = @ForeignKey(name = "FK_Vehiculo_Transportadora"))
    private Transportadora transportadora;
}
