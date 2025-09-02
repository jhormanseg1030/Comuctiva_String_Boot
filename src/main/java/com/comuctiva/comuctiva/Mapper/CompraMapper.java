package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.models.Compra;

public interface CompraMapper {
Compra toCompra(CompraDto compraDto);
CompraDto toCompraDto(Compra compra);
List<CompraDto> toCompraDtoList(List<Compra>compras);
void updateCompra(Compra compra, CompraDto compraDto);
}
