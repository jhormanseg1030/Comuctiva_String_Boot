    package com.comuctiva.comuctiva.Mapper;

    import java.util.ArrayList;
    import java.util.List;

    import org.springframework.stereotype.Component;

    import com.comuctiva.comuctiva.Dto.CompraDto;
    import com.comuctiva.comuctiva.models.Compra;

    @Component
    public class CompraMapperImple implements CompraMapper {

        @Override
        public Compra toCompra(CompraDto compraDto){
        if (compraDto == null) {
            return null;
        }
        Compra compra = new Compra();
        compra.setId_compra(compraDto.getId_comp());
        compra.setFec_com(compraDto.getFec_comp());
        compra.setRef_pago(compraDto.getRef_pag());
        compra.setTotal(compraDto.getTot());
        return compra;
        }
        @Override
        public CompraDto toCompraDto(Compra compra){
        if (compra ==null) {
            return null;
        }
        CompraDto compraDto = new CompraDto();
        compraDto.setFec_comp(compra.getFec_com());
        compraDto.setId_comp(compra.getId_compra());
        compraDto.setRef_pag(compra.getRef_pago());
        compraDto.setTot(compra.getTotal());
        return compraDto;
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
