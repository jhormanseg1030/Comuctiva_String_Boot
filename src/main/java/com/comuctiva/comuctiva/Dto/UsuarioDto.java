package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDto {
    private Integer id_usu;
    private String nom_u;
    private String ape;
    private String ape2;
    private Long tele;
    private Long tele2;
    private String corr;
    private Byte numdocu;
    private String passw;
    private Short tipdocuId;
}
