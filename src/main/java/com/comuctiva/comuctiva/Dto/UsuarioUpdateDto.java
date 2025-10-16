package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioUpdateDto {
    @NotNull
    private Integer id_us;
    private String nomb;
    @NotBlank
    private String apell;
    private String apell2;
    private Long telefo;
    private Long telefo2;
    private String corr;
    private Long numdocument;
    private String passwo;
    @NotNull
    private Integer tipDocuId;
}
