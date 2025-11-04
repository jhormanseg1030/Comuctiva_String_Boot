package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.ProductoCreateDto;
import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.models.Unidad_Medida;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.Unidad_MedidaRepositories;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ProductoMapperImple implements ProductoMapper{

   private final Unidad_MedidaRepositories unidad_MedidaRepositories;
   private final UsuarioRepositories usuarioRepositories;

   public ProductoMapperImple(Unidad_MedidaRepositories unidad_MedidaRepositories,
                           UsuarioRepositories usuarioRepositories){
      this.unidad_MedidaRepositories = unidad_MedidaRepositories;
      this.usuarioRepositories = usuarioRepositories;
   }

   @Override
   public Producto toProducto(ProductoCreateDto productoCreateDto){
      Producto producto = new Producto();
      producto.setNomprod(productoCreateDto.getNombre_Producto());
      producto.setValor(productoCreateDto.getValor());
      producto.setCant(productoCreateDto.getCantidad());
      producto.setImagen(productoCreateDto.getImagen());
      producto.setDescrip(productoCreateDto.getDescripcion());
      producto.setCategoria(productoCreateDto.getCategoria());
      producto.setEstado(productoCreateDto.getEstado()); // 🆕 Asignar estado

      Unidad_Medida unidad_Medida = unidad_MedidaRepositories.findById(productoCreateDto.getId_medida())
      .orElseThrow(() -> new EntityNotFoundException("Unidad de medida no encontrada"));
      producto.setUnidad_Medida(unidad_Medida);
      
      // 🆕 Asignar vendedor si se proporciona
      if (productoCreateDto.getId_usuario() != null) {
         Usuario vendedor = usuarioRepositories.findById(productoCreateDto.getId_usuario())
            .orElseThrow(() -> new EntityNotFoundException("Usuario vendedor no encontrado"));
         producto.setVendedor(vendedor);
      }
      
      return producto;
   }

@Override
public ProductoDto toProductoDto(Producto producto) {
   ProductoDto dto = new ProductoDto();
   dto.setId_pro(producto.getId_producto());
   dto.setNombre_Producto(producto.getNomprod());
   dto.setValor(producto.getValor());
   dto.setCantidad(producto.getCant());
   dto.setImagen(producto.getImagen());
   dto.setDescripcion(producto.getDescrip());
   dto.setCategoria(producto.getCategoria());
   dto.setId_medida(producto.getUnidad_Medida() != null ? producto.getUnidad_Medida().getId_Medida() : null);
   dto.setEstado(producto.getEstado());
   dto.setActivo(producto.getActivo());
   return dto;
}
}