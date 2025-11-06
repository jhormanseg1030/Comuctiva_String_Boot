package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.Com_ProducDto;
import com.comuctiva.comuctiva.Mapper.Comp_producMapper;
import com.comuctiva.comuctiva.models.Comp_Produc;
import com.comuctiva.comuctiva.repositoryes.Comp_ProductRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Comp_ProducServicesImple implements Comp_ProducServices {
    private final Comp_ProductRepositories compProductoRepositories;
    private final Comp_producMapper compProductoMapper;

    public Comp_ProducServicesImple(Comp_ProductRepositories compProductoRepositories,
            Comp_producMapper compProductoMapper) {
        this.compProductoRepositories = compProductoRepositories;
        this.compProductoMapper = compProductoMapper;
    }

    @Override
    public List<Com_ProducDto> obtenerProductosPorCompra(Integer id_compra) {
        List<Comp_Produc> productos = compProductoRepositories.findByCompra_Id_compra(id_compra);
        return productos.stream()
                .map(compProductoMapper::toCompraProductoDto)
                .collect(Collectors.toList());
    }

    @Override
    public Com_ProducDto obtenerProductoCompra(Integer id_com_produc) {
        Comp_Produc compProduc = compProductoRepositories.findById(id_com_produc)
                .orElseThrow(() -> new EntityNotFoundException("Producto de compra no encontrado con ID: " + id_com_produc));
        return compProductoMapper.toCompraProductoDto(compProduc);
    }
}
