package com.comuctiva.comuctiva.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.Com_ProducCreateDto;
import com.comuctiva.comuctiva.Dto.CompraCreateDto;
import com.comuctiva.comuctiva.Dto.CompraDto;
import com.comuctiva.comuctiva.Dto.VentaDto;
import com.comuctiva.comuctiva.Mapper.CompraMapper;
import com.comuctiva.comuctiva.Mapper.VentaMapper;
import com.comuctiva.comuctiva.models.Comp_Produc;
import com.comuctiva.comuctiva.models.Compra;
import com.comuctiva.comuctiva.models.Pedidos;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.models.Tipo_De_Pago;
import com.comuctiva.comuctiva.repositoryes.*;


@Service
public class CompraServicesImple implements CompraServices{

    private final Comp_ProductRepositories comp_ProductRepositories;
    
    @Autowired
    private CompraRepositories compraRepositories;

    @Autowired
    private PedidoRepositorie pedidosRepositories;

    @Autowired
    private Tipo_De_PagoRepositories tipoPagoRepositories;

    @Autowired
    private ProductoRepositorie productoRepositories;

    @Autowired
    private CompraMapper compraMapper;

    @Autowired
    private VentaMapper ventaMapper;

    CompraServicesImple(Comp_ProductRepositories comp_ProductRepositories) {
        this.comp_ProductRepositories = comp_ProductRepositories;
    }

    // 🆕 Crear una compra CON LOS DATOS CORRECTOS
@Override
public CompraDto crearCompra(CompraCreateDto compraCreateDto, Integer id_usuario) {
    try {
        // ✅ VALIDAR QUE TODOS LOS DATOS EXISTAN
        Pedidos pedido = pedidosRepositories.findById(compraCreateDto.getId_pedido())
            .orElseThrow(() -> new IllegalStateException("Pedido no encontrado"));

        Tipo_De_Pago tipoPago = tipoPagoRepositories.findById(compraCreateDto.getId_ti_pago())
            .orElseThrow(() -> new IllegalStateException("Tipo de pago no encontrado"));

        // ✅ CREAR COMPRA
        Compra compra = new Compra();
        compra.setFec_com(LocalDateTime.now());
        compra.setRef_pago(compraCreateDto.getRef_pago());
        compra.setPedido(pedido);
        compra.setTipo_pago(tipoPago);

        // Calcular total desde los productos
        Double total = 0.0;
        if (compraCreateDto.getProductos() != null && !compraCreateDto.getProductos().isEmpty()) {
            for (Com_ProducCreateDto prod : compraCreateDto.getProductos()) {
                total += prod.getPrecio() * prod.getCantidad();
            }
        }
        compra.setTotal(total);

        // ✅ GUARDAR COMPRA
        Compra compraSaved = compraRepositories.save(compra);

        // ✅ GUARDAR PRODUCTOS DE LA COMPRA
        List<Comp_Produc> productosGuardados = new ArrayList<>();
        if (compraCreateDto.getProductos() != null && !compraCreateDto.getProductos().isEmpty()) {
            for (Com_ProducCreateDto prodDto : compraCreateDto.getProductos()) {
                Producto producto = productoRepositories.findById(prodDto.getId_producto())
                    .orElseThrow(() -> new IllegalStateException("Producto no encontrado"));

                Comp_Produc compProduc = new Comp_Produc();
                compProduc.setCompra(compraSaved);
                compProduc.setProducto(producto);
                compProduc.setCantidad(prodDto.getCantidad());
                compProduc.setPrecio(prodDto.getPrecio());

                Comp_Produc saved = comp_ProductRepositories.save(compProduc);
                productosGuardados.add(saved);
            }
        }

        // ✅ ASIGNAR PRODUCTOS A LA COMPRA ANTES DE RETORNAR
        compraSaved.setProductos(productosGuardados);

        return compraMapper.toCompraDto(compraSaved);

    } catch (IllegalStateException ex) {
        throw ex;
    } catch (Exception ex) {
        throw new RuntimeException("Error al crear compra: " + ex.getMessage());
    }
}

    @Override
    public CompraDto obtenerCompraPorId(Integer id_compra) {
        return compraRepositories.findById(id_compra)
            .map(compraMapper::toCompraDto)
            .orElse(null);
    }

    @Override
    public List<CompraDto> obtenerMisCompras(Integer id_usuario) {
        List<Compra> compras = compraRepositories.findComprasByUsuario(id_usuario);
        return compras.stream()
            .map(compraMapper::toCompraDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<VentaDto> obtenerMisVentas(Integer id_usuario) {
        return comp_ProductRepositories.findVentasByVendedor(id_usuario).stream()
            .map(ventaMapper::toVentaDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<CompraDto> listarCompras() {
        return compraRepositories.findAll().stream()
            .map(compraMapper::toCompraDto)
            .collect(Collectors.toList());
    }
}
