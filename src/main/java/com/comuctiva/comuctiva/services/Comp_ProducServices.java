package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.Com_ProducDto;

public interface Comp_ProducServices {
    List<Com_ProducDto> obtenerProductosPorCompra(Integer id_compra);
    
    // 🆕 Obtener un producto de compra específico
    Com_ProducDto obtenerProductoCompra(Integer id_com_produc);
}
