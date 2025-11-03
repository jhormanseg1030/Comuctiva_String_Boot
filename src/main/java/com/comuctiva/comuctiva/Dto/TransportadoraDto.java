package com.comuctiva.comuctiva.Dto;

import lombok.Data;

@Data
public class TransportadoraDto {
    private Integer id_trans;
    private String log;
    private String telefo;  // Cambiado de Long a String
    private String nombt;
    private String correo;
    private String direc;
    private String siti_web;
}
