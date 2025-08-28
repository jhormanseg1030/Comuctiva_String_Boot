package com.comuctiva.comuctiva.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompraDto {
    private Integer Id_Comp;
    private Short Tot;
    private String Ref_Pag;
    private LocalDateTime Fec_comp;
}
