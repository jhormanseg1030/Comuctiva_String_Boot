package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.CompraCreateDto;
import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.Dto.VentaDto;

public interface CompraServices {
    // 🆕 Crear una compra
    CompraDto crearCompra(CompraCreateDto compraCreateDto, Integer id_usuario);
    
    // 🆕 Obtener compra por ID
    CompraDto obtenerCompraPorId(Integer id_compra);
    
    // 🆕 Obtener mis compras (como cliente)
    List<CompraDto> obtenerMisCompras(Integer id_usuario);
    
    // 🆕 Obtener mis ventas (productos que vendí)
    List<VentaDto> obtenerMisVentas(Integer id_usuario);
    
    // 🆕 Listar todas las compras
    List<CompraDto> listarCompras();
}
