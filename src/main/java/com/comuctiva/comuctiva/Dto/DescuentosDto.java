package com.comuctiva.comuctiva.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DescuentosDto {
    private Integer id_descu;
    private String descrip;
    private LocalDateTime fec_des;
    private BigDecimal val;
}
