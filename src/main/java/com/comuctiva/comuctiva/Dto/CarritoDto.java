package com.comuctiva.comuctiva.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDto {
    private Integer idCarrito;
    private Integer id_usuario;
    private List<Produc_CarriDto> items;
    private Integer totalItems;
    private Double total;
}
