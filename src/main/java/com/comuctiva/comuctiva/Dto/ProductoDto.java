package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDto {
    private Integer  Id_Pro;
    private String NoPro;
    private Double Valoor;
    private Short Cantid;
    private String Image;
    private String Descri;

}
