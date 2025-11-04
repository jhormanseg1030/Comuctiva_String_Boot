package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.MiCompraDto;
import com.comuctiva.comuctiva.Dto.VentaDto;

public interface VentasServices {
    
    /**
     * Obtiene las ventas de un vendedor específico
     * (productos que le compraron al usuario)
     * @param idVendedor ID del usuario vendedor
     * @return Lista de ventas
     */
    List<VentaDto> obtenerMisVentas(Integer idVendedor);
    
    /**
     * Obtiene las compras de un cliente específico
     * (productos que el usuario compró)
     * @param idCliente ID del usuario comprador
     * @return Lista de compras
     */
    List<MiCompraDto> obtenerMisCompras(Integer idCliente);
}
