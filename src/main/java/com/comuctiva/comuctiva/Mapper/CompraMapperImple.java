    package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;
import com.comuctiva.comuctiva.Dto.CompraCreateDto;
import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.models.Compra;
import com.comuctiva.comuctiva.models.Pedidos;
import com.comuctiva.comuctiva.models.Tipo_De_Pago;
import com.comuctiva.comuctiva.repositoryes.PedidoRepositorie;
import com.comuctiva.comuctiva.repositoryes.Tipo_De_PagoRepositories;

import jakarta.persistence.EntityNotFoundException;

    @Component
    public class CompraMapperImple implements CompraMapper {

        private final Tipo_De_PagoRepositories tipo_De_PagoRepositories;
        private final PedidoRepositorie pedidoRepositorie;

        public CompraMapperImple(Tipo_De_PagoRepositories tipo_De_PagoRepositories, PedidoRepositorie pedidoRepositorie){
            this.tipo_De_PagoRepositories = tipo_De_PagoRepositories;
            this.pedidoRepositorie = pedidoRepositorie;
        }

        @Override
        public Compra toCompra(CompraCreateDto compraCreateDto){

            if (compraCreateDto == null) {
                return null;
            }
            Compra compra = new Compra();
            compra.setTotal(compraCreateDto.getTotal());
            compra.setRef_pago(compraCreateDto.getReferencia_pago());
            compra.setFec_com(compraCreateDto.getFecha_compra());

            Tipo_De_Pago tipo_De_Pago = tipo_De_PagoRepositories.findById(compraCreateDto.getId_tipago())
            .orElseThrow(() -> new EntityNotFoundException("Tipo de pago no encontrado"));
            compra.setTipo_pago(tipo_De_Pago);

            Pedidos pedido = pedidoRepositorie.findById(compraCreateDto.getId_pedido())
            .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
            compra.setPedido(pedido);
            return compra;
        }

        @Override
        public CompraDto toCompraDto(Compra compra){
            return new CompraDto(
                compra.getId_compra(),
                compra.getTotal(),
                compra.getRef_pago(),
                compra.getFec_com(),
                compra.getTipo_pago().getId_tipago(),
                compra.getPedido().getId_pedido());
        }
    }
