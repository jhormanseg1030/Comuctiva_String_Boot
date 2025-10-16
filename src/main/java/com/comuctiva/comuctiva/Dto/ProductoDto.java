package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDto {
    private Integer id_pro;
    private String nopro;
    private Double valoor;
    private Short cantid;
    private String image;
    private String descri;
    private Integer id_medi;
}
