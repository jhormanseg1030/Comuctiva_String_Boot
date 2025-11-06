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
    public ProductoDto crearProducto(ProductoCreateDto productoCreateDto) {
        // Validar que la unidad de medida exista
        Unidad_Medida unidadMedida = unidad_MedidaRepositories.findById(productoCreateDto.getId_medida())
                .orElseThrow(() -> new IllegalStateException("Unidad de medida no encontrada con id: " + productoCreateDto.getId_medida()));
                Producto producto = productoMapper.toProducto(productoCreateDto);
                producto.setUnidad_Medida(unidadMedida);
        

    if (productoCreateDto.getId_usuario() != null) {
        Usuario vendedor = usuarioRepositories.findById(productoCreateDto.getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        producto.setVendedor(vendedor);
    }
        Producto guardado = productoRepositorie.save(producto);
        return productoMapper.toProductoDto(guardado);
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
        // ✅ Solo listar productos activos
        List<Producto> productos = productoRepositorie.findByActivoTrue();
        return productos.stream()
            .map(productoMapper::toProductoDto)
            .collect(Collectors.toList());
    }

    @Override
    public void eliminarProducto(Integer id_pro) {
        Producto productoElimi = productoRepositorie.findById(id_pro)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado con id: " + id_pro));
        productoRepositorie.delete(productoElimi);
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

    @Override
    public java.util.List<ProductoDto> listarPendientes() {
        return productoRepositorie.findByEstado("pendiente")
                .stream()
                .map(productoMapper::toProductoDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void cambiarEstadoProducto(Integer id, String nuevoEstado) {
        Producto producto = productoRepositorie.findById(id)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado con id: " + id));
        producto.setEstado(nuevoEstado);
        productoRepositorie.save(producto);
    }

        @Override
        public List<ProductoDto> listarMisProductos(Integer idUsuario) {
        List<Producto> productos = productoRepositorie.findByVendedorId_usuario(idUsuario);
        return productos.stream()
            .map(productoMapper::toProductoDto)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void desactivarProducto(Integer id) {
        if (!productoRepositorie.existsById(id)) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        productoRepositorie.softDelete(id);
    }
    
    // ✅ NUEVO: Restaurar producto
    @Override
    @Transactional
    public void restaurarProducto(Integer id) {
        if (!productoRepositorie.existsById(id)) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        productoRepositorie.restore(id);
    }
    @Override
    @Transactional
        public List<ProductoDto> obtenerProductosPorVendedor(Integer id_usuario) {
        return productoRepositorie.findByVendedor_Id_Usuario(id_usuario)
            .stream()
            .map(productoMapper::toProductoDto)
            .toList();
    }
    @Override
    @Transactional
        public ProductoDto obtenerProductoPorIdYVendedor(Integer id_producto, Integer id_usuario) {
        return productoRepositorie.findByIdAndVendedor(id_producto, id_usuario)
            .map(productoMapper::toProductoDto)
            .orElse(null);
    }
    @Override
    @Transactional
        public ProductoDto actualizarProductoVendedor(Integer id_producto, Integer id_usuario, ProductoUpdateDto dto) {
        var producto = productoRepositorie.findByIdAndVendedor(id_producto, id_usuario)
            .orElse(null);
        
        if (producto == null) return null;
        
        if (dto.getNombre_Producto() != null) producto.setNomprod(dto.getNombre_Producto());
        if (dto.getValor() != null) producto.setValor(dto.getValor());
        if (dto.getCantidad() != null) producto.setCant(dto.getCantidad());
        if (dto.getImagen() != null) producto.setImagen(dto.getImagen());
        if (dto.getDescripcion() != null) producto.setDescrip(dto.getDescripcion());
        if (dto.getCategoria() != null) producto.setCategoria(dto.getCategoria());
        if (dto.getEstado() != null) producto.setEstado(dto.getEstado());

        productoRepositorie.save(producto);
        return productoMapper.toProductoDto(producto);
    }
    @Override
    @Transactional
        public boolean desactivarProductoVendedor(Integer id_producto, Integer id_usuario) {
        var producto = productoRepositorie.findByIdAndVendedor(id_producto, id_usuario)
            .orElse(null);
        
        if (producto == null) return false;
        
        producto.setActivo(false);
        productoRepositorie.save(producto);
        return true;
    }
    @Override
    @Transactional
        public boolean activarProductoVendedor(Integer id_producto, Integer id_usuario) {
        var producto = productoRepositorie.findByIdAndVendedor(id_producto, id_usuario)
            .orElse(null);
        
        if (producto == null) return false;
        
        producto.setActivo(true);
        productoRepositorie.save(producto);
        return true;
    }
}
