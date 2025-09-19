package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReembolsosDto {
    private Integer id_rem;
    private LocalDateTime fe_solici;
    private Long val;
    private String mot;
    private LocalDateTime fe_res;
    private String esta;
    private Integer id_compra;

}
