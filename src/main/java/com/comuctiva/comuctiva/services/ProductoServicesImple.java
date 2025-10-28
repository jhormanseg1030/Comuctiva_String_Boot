package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.ProductoCreateDto;
import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.Dto.ProductoUpdateDto;
import com.comuctiva.comuctiva.Mapper.ProductoMapper;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.models.Unidad_Medida;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.ProductoRepositorie;
import com.comuctiva.comuctiva.repositoryes.Unidad_MedidaRepositories;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

@Service
public class ProductoServicesImple implements ProductoServices {

    // Método eliminado: listarPorDocumentoVendedor - No hay relación Producto-Usuario

    private final ProductoRepositorie productoRepositorie;
    private final ProductoMapper productoMapper;
    private final Unidad_MedidaRepositories unidad_MedidaRepositories;
    private final UsuarioRepositories usuarioRepositories;

    public ProductoServicesImple(ProductoRepositorie productoRepositorie, ProductoMapper productoMapper, Unidad_MedidaRepositories unidad_MedidaRepositories, UsuarioRepositories usuarioRepositories) {
        this.productoRepositorie = productoRepositorie;
        this.productoMapper = productoMapper;
        this.unidad_MedidaRepositories = unidad_MedidaRepositories;
        this.usuarioRepositories = usuarioRepositories;
    }

    @Override
    @Transactional
    public ProductoDto crearProducto(ProductoCreateDto productoCreateDto, String documentoVendedor) {
        // Buscar el usuario vendedor
        Long numDoc;
        try {
            numDoc = Long.parseLong(documentoVendedor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Documento de vendedor inválido");
        }
        
        // Ya no se necesita asociar con usuario/vendedor
        Producto producto = productoMapper.toProducto(productoCreateDto);
        Producto productoGuardado = productoRepositorie.save(producto);
        return productoMapper.toProductoDto(productoGuardado);
    }

    @Override
    @Transactional()
    public ProductoDto productoPorId(Integer id) {
        Producto producto = productoRepositorie.findById(id)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado con id:"));
        return productoMapper.toProductoDto(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDto> listar() {
        return productoRepositorie.findAll()
                .stream()
                .map(productoMapper::toProductoDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProducto(Integer id) {
        Producto productoElimi = productoRepositorie.findById(id)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado con id: " + id));
        productoRepositorie.save(productoElimi);
    }

    @Override
    @Transactional
    public ProductoDto actualizarProducto(ProductoUpdateDto productoUpdateDto) {
        Producto producto = productoRepositorie.findById(productoUpdateDto.getId_producto())
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado con id: "));

        producto.setNomprod(productoUpdateDto.getNombre_Producto());
        producto.setValor(productoUpdateDto.getValor());
        producto.setCant(productoUpdateDto.getCantidad());
        producto.setImagen(productoUpdateDto.getImagen());
        producto.setDescrip(productoUpdateDto.getDescripcion());

        Unidad_Medida unidad_Medida = unidad_MedidaRepositories.findById(productoUpdateDto  .getId_medida())
                .orElseThrow(() -> new IllegalStateException("Unidad de medida no encontrada"));
        producto.setUnidad_Medida(unidad_Medida);

        Producto productoGuardado = productoRepositorie.save(producto);
        return productoMapper.toProductoDto(productoGuardado);
    }
}
