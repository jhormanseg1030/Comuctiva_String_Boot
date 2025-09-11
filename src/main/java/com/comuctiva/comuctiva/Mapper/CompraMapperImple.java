    package com.comuctiva.comuctiva.Mapper;

    import java.util.ArrayList;
    import java.util.List;

    import org.springframework.stereotype.Component;

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
        public Compra toCompra(CompraDto compraDto){
            Compra compra = new Compra();
            compra.setId_compra(compraDto.getId_comp());
            compra.setTotal(compraDto.getTot());
            compra.setRef_pago(compraDto.getRef_pag());
            compra.setFec_com(compraDto.getFec_comp());

            Tipo_De_Pago tipo_De_Pago = tipo_De_PagoRepositories.findById(compraDto.getId_tipago())
            .orElseThrow(() -> new EntityNotFoundException("Tipo de pago no encontrado"));
            compra.setTipo_pago(tipo_De_Pago);

            Pedidos pedido = pedidoRepositorie.findById(compraDto.getId_pedi())
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

        @Override
        public List<CompraDto> toCompraDtoList(List<Compra>compras){
            if (compras== null) {
                return List.of();
            }
            List<CompraDto>compraDtos=new ArrayList<CompraDto>(compras.size());
            for(Compra compra : compras){
                compraDtos.add(toCompraDto(compra));
            }
            return compraDtos;
        }
        @Override
        public void updateCompra(Compra compra, CompraDto compraDto){
            if (compraDto == null) {
                return;
            }
            compra.setId_compra(compraDto.getId_comp());
            compra.setFec_com(compraDto.getFec_comp());
            compra.setRef_pago(compraDto.getRef_pag());
            compra.setTotal(compraDto.getTot());
        }
    }
