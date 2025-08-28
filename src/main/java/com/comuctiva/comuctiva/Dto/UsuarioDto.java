package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDto {
    private Integer Id_Usu;
    private String Nom_U;
    private String Ape;
    private String Ape2;
    private Long Tele;
    private Long Tele2;
    private String Corr;
    private Byte NumDocu;
    private String Passw;
}
