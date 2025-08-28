package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReembolsosDto {
    private Integer Id_Rem;
    private LocalDateTime Fe_solici;
    private Long Val;
    private String Mot;
    private LocalDateTime Fe_res;
    private String Esta;

}
