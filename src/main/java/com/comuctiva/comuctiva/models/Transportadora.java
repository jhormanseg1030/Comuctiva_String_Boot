package com.comuctiva.comuctiva.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Transportadora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_transpor;

    @NotBlank(message = "El logo es obligatorio")
    @Size(max = 255, message = "El logo no puede exceder 255 caracteres")
    @Column(nullable = false, length = 255)
    private String logo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[+\\d][\\d\\s-]{6,20}$", message = "Formato de teléfono inválido")
    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    @Column(nullable = false, length = 20)
    private String telefono;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(name = "nombret", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    @Size(max = 150, message = "El correo no puede exceder 150 caracteres")
    @Column(nullable = false, length = 150)
    private String correo;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    @Column(name = "direcc", nullable = false, length = 200)
    private String direccion;

    @NotBlank(message = "El sitio web es obligatorio")
    @Size(max = 255, message = "El sitio web no puede exceder 255 caracteres")
    @Column(nullable = false, length = 255)
    private String sitio_web;
}
