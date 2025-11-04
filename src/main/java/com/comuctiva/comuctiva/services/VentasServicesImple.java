package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.MiCompraDto;
import com.comuctiva.comuctiva.Dto.VentaDto;
import com.comuctiva.comuctiva.models.Comp_Produc;
import com.comuctiva.comuctiva.repositoryes.Comp_ProducRepositories;

@Service
public class VentasServicesImple implements VentasServices {
    
    private final Comp_ProducRepositories compProducRepositories;
    
    public VentasServicesImple(Comp_ProducRepositories compProducRepositories) {
        this.compProducRepositories = compProducRepositories;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<VentaDto> obtenerMisVentas(Integer idVendedor) {
        List<Comp_Produc> ventas = compProducRepositories.findVentasByVendedor(idVendedor);
        
        return ventas.stream()
            .map(cp -> {
                VentaDto dto = new VentaDto();
                dto.setId_compra(cp.getCompra().getId_compra());
                dto.setId_producto(cp.getProduc().getId_producto());
                dto.setProducto_nombre(cp.getProduc().getNomprod());
                dto.setCliente_nombre(cp.getCompra().getPedido().getUsuario().getNom_Usu() + " " + 
                                     cp.getCompra().getPedido().getUsuario().getApell1());
                dto.setId_cliente(cp.getCompra().getPedido().getUsuario().getId_Usuario());
                dto.setCantidad(cp.getCant());
                dto.setValor(cp.getValor());
                dto.setTotal(cp.getValor() * cp.getCant());
                dto.setFecha(cp.getCompra().getFec_com());
                return dto;
            })
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MiCompraDto> obtenerMisCompras(Integer idCliente) {
        List<Comp_Produc> compras = compProducRepositories.findComprasByCliente(idCliente);
        
        return compras.stream()
            .map(cp -> {
                MiCompraDto dto = new MiCompraDto();
                dto.setId_compra(cp.getCompra().getId_compra());
                dto.setId_producto(cp.getProduc().getId_producto());
                dto.setProducto_nombre(cp.getProduc().getNomprod());
                
                // El vendedor puede ser null si el producto no tiene vendedor asignado
                if (cp.getProduc().getVendedor() != null) {
                    dto.setVendedor_nombre(cp.getProduc().getVendedor().getNom_Usu() + " " + 
                                          cp.getProduc().getVendedor().getApell1());
                    dto.setId_vendedor(cp.getProduc().getVendedor().getId_Usuario());
                } else {
                    dto.setVendedor_nombre("COMUCTIVA");
                    dto.setId_vendedor(null);
                }
                
                dto.setCantidad(cp.getCant());
                dto.setValor(cp.getValor());
                dto.setTotal(cp.getValor() * cp.getCant());
                dto.setFecha(cp.getCompra().getFec_com());
                return dto;
            })
            .collect(Collectors.toList());
    }
}
