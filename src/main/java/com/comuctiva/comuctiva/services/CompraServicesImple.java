package com.comuctiva.comuctiva.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.CompraCreateDto;
import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.Dto.CompraUpdateDto;
import com.comuctiva.comuctiva.Mapper.CompraMapper;
import com.comuctiva.comuctiva.models.Compra;
import com.comuctiva.comuctiva.models.Pedidos;
import com.comuctiva.comuctiva.models.Tipo_De_Pago;
import com.comuctiva.comuctiva.repositoryes.CompraRepositories;
import com.comuctiva.comuctiva.repositoryes.PedidoRepositorie;
import com.comuctiva.comuctiva.repositoryes.Tipo_De_PagoRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompraServicesImple implements CompraServices{
    private final CompraRepositories compraRepositories;
    private final CompraMapper compraMapper;
    private final Tipo_De_PagoRepositories tipo_De_PagoRepositories;
    private final PedidoRepositorie pedidoRepositorie;

    public CompraServicesImple(CompraRepositories compraRepositories, CompraMapper compraMapper,
    Tipo_De_PagoRepositories tipo_De_PagoRepositories, PedidoRepositorie pedidoRepositorie){
    
    this.compraRepositories = compraRepositories;
    this.compraMapper = compraMapper;
    this.tipo_De_PagoRepositories = tipo_De_PagoRepositories;
    this.pedidoRepositorie = pedidoRepositorie;
    }

    @Override
    @Transactional
    public CompraDto crearCompra(CompraCreateDto compraCreateDto){
        Compra compra = compraMapper.toCompra(compraCreateDto);
        Compra compraGuardada = compraRepositories.save(compra);
        return compraMapper.toCompraDto(compraGuardada);
    }

    @Override
    @Transactional()
    public CompraDto compraPorId(Integer id){
        Compra compra = compraRepositories.findById(id)
        .orElseThrow(()-> new EntityNotFoundException("Compra no encontrada"));
        return compraMapper.toCompraDto(compra);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompraDto> listartodos(){
    return compraRepositories.findAll()
        .stream()
        .map(compraMapper :: toCompraDto)
        .toList();
    }

    @Override
    public void eliminarCompra(Integer id){
        Compra compraEliminada = compraRepositories.findById(id)
        .orElseThrow(()-> new EntityNotFoundException("Compra no encontrada"));
        compraRepositories.save(compraEliminada);
    }

    @Override
    @Transactional
    public CompraDto actualizarCompra(CompraUpdateDto compraUpdateDto){
        Compra compra = compraRepositories.findById(compraUpdateDto.getId_compra())
        .orElseThrow(()-> new EntityNotFoundException("Compra no encontrada"));

        compra.setTotal(compraUpdateDto.getTotal());
        compra.setRef_pago(compraUpdateDto.getReferencia_pago());
        compra.setFec_com(compraUpdateDto.getFecha_compra());

        Tipo_De_Pago tipo_De_Pago = tipo_De_PagoRepositories.findById(compraUpdateDto.getId_tipago())
        .orElseThrow(()-> new EntityNotFoundException("Tipo de pago no encontrado"));
        compra.setTipo_pago(tipo_De_Pago);

        Pedidos pedido = pedidoRepositorie.findById(compraUpdateDto.getId_pedido())
        .orElseThrow(()-> new EntityNotFoundException("Pedido no encontrado"));
        compra.setPedido(pedido);
        Compra compraGuardada = compraRepositories.save(compra);
        return compraMapper.toCompraDto(compraGuardada);
    }
}
