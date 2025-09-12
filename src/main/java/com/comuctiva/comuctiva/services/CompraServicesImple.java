package com.comuctiva.comuctiva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.Mapper.CompraMapper;
import com.comuctiva.comuctiva.models.Compra;
import com.comuctiva.comuctiva.repositoryes.CompraRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompraServicesImple implements CompraServices{
    private final CompraRepositories compraRepositories;
    private final CompraMapper compraMapper;

    public CompraServicesImple(CompraRepositories compraRepositories, CompraMapper compraMapper) {
    this.compraRepositories = compraRepositories;
    this.compraMapper = compraMapper;
    }
    @Override
    public CompraDto crearCompra(CompraDto compraDto){
        Compra compra = compraMapper.toCompra(compraDto);
        Compra compraGuardada = compraRepositories.save(compra);
            return compraMapper.toCompraDto(compraGuardada);
    }
    @Override
    public CompraDto compraPorId(Integer id){
        return compraRepositories.findById(id)
        .map(compraMapper :: toCompraDto)
        .orElseThrow(()-> new EntityNotFoundException("Compra no encontrada"));
    }
    @Override
    public List<CompraDto> listartodos(){
    return compraRepositories.findAll()
        .stream()
        .map(compraMapper :: toCompraDto)
        .toList();
    }
    @Override
    public void eliminarCompra(Integer id){
        compraRepositories.deleteById(id);
    }
}
