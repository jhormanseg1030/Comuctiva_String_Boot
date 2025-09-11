package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Califi_ProduDto {
    private Integer id_califi;
    private String coment;
    private LocalDateTime fec_calif;
    private Short estre;
    private Integer id_produ;
    private Integer id_usua;
}
