package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioCreateDto {
    @NotBlank
    private String nombre;
    private String apellido;
    private String apellido2;
    private Long telefono;
    private Long telefono2;
    private String correo;
    private Short numdocumento;
    private String password;
    
    @NotNull
    private Integer tipId;
}
