package com.comuctiva.comuctiva.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class CompraCreateDto {
    
    @NotNull(message = "El ID del pedido es requerido")
    private Integer id_pedido;
    
    @NotNull(message = "El tipo de pago es requerido")
    private Integer id_ti_pago;
    
    private String ref_pago;
    
    @NotNull(message = "Debe incluir al menos un producto")
    private List<Com_ProducCreateDto> productos;
}
